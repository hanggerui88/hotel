DROP TRIGGER IF EXISTS rr_add;
DELIMITER $$
CREATE TRIGGER rr_add AFTER INSERT ON RmStatusRule FOR EACH ROW 
BEGIN
    DECLARE v_ddate DATE;
    DECLARE v_status_exists INT;
    DECLARE v_rooms INT;
    
    -- 只处理当前日期及未来14天内的日期
    SET v_ddate = GREATEST(NEW.date_start, CURDATE());
    
    -- 遍历规则日期范围
    WHILE v_ddate <= NEW.date_end AND v_ddate <= CURDATE() + INTERVAL 13 DAY DO
        -- 规则作用于特定房间类型
        IF NEW.rmtype_id IS NOT NULL THEN
            -- 获取该房型下的所有房间数量
            SELECT COUNT(*) INTO v_rooms 
            FROM Room 
            WHERE roomtype_id = NEW.rmtype_id;
            
            -- 检查该房型在该日期是否有状态记录
            SELECT COUNT(*) INTO v_status_exists 
            FROM RmDailyStatus 
            WHERE ddate = v_ddate 
            AND rm_number IN (SELECT rm_number FROM Room WHERE roomtype_id = NEW.rmtype_id);
            
            -- 如果记录数不完整，创建缺失记录
            IF v_status_exists < v_rooms THEN
                INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
                SELECT v_ddate, rm_number, 0 -- 默认状态
                FROM Room 
                WHERE roomtype_id = NEW.rmtype_id
                AND rm_number NOT IN (
                    SELECT rm_number 
                    FROM RmDailyStatus 
                    WHERE ddate = v_ddate
                );
            END IF;
            
            -- 更新状态为规则指定的状态
            UPDATE RmDailyStatus
            SET status_number = NEW.status_number
            WHERE ddate = v_ddate
            AND rm_number IN (SELECT rm_number FROM Room WHERE roomtype_id = NEW.rmtype_id);
            
        -- 规则作用于特定房间
        ELSEIF NEW.rm_number IS NOT NULL THEN
            -- 检查该房间在该日期是否有状态记录
            SELECT COUNT(*) INTO v_status_exists 
            FROM RmDailyStatus 
            WHERE ddate = v_ddate 
            AND rm_number = NEW.rm_number;
            
            -- 如果记录不存在，创建新记录
            IF v_status_exists = 0 THEN
                INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
                VALUES (v_ddate, NEW.rm_number, NEW.status_number);
            ELSE
                -- 更新状态为规则指定的状态
                UPDATE RmDailyStatus
                SET status_number = NEW.status_number
                WHERE ddate = v_ddate
                AND rm_number = NEW.rm_number;
            END IF;
        END IF;
        
        SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
    END WHILE;
END
$$
DELIMITER ;
-- 
-- delete from RmStatusRule;
-- -- 1. 创建房间类型
-- 
-- 
-- -- 3. 创建房间状态规则
-- INSERT INTO RmStatusRule (
--     date_start, date_end, rm_number, 
--     status_number, notes
-- ) VALUES (
--     CURDATE(), CURDATE() + INTERVAL 3 DAY, 101,
--     2, '房间维修'
-- );
-- 
-- -- 4. 检查创建的每日状态
-- SELECT * 
-- FROM RmDailyStatus 
-- WHERE rm_number = 1001 
--   AND ddate BETWEEN CURDATE() AND CURDATE() + INTERVAL 14 DAY
-- ORDER BY ddate;

-- 修正后的状态规则更新后触发器
DROP TRIGGER IF EXISTS rr_after;
DELIMITER $$
CREATE TRIGGER rr_after AFTER UPDATE ON RmStatusRule FOR EACH ROW
BEGIN
    DECLARE v_ddate DATE;
    DECLARE v_end_date DATE;

    -- 只处理当前日期及未来14天内的日期
    SET v_ddate = GREATEST(NEW.date_start, CURDATE());
    SET v_end_date = LEAST(NEW.date_end, CURDATE() + INTERVAL 13 DAY);
    
    -- 规则作用于房型
    IF NEW.rmtype_id IS NOT NULL AND v_ddate <= v_end_date THEN
        WHILE v_ddate <= v_end_date DO
            -- 创建/更新房型下所有房间的记录
            INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
            SELECT v_ddate, rm_number, NEW.status_number
            FROM Room
            WHERE roomtype_id = NEW.rmtype_id
            ON DUPLICATE KEY UPDATE status_number = NEW.status_number;
            
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    
    -- 规则作用于房间
    ELSEIF NEW.rm_number IS NOT NULL AND v_ddate <= v_end_date THEN
        WHILE v_ddate <= v_end_date DO
            -- 创建/更新该房间的记录
            INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
            VALUES (v_ddate, NEW.rm_number, NEW.status_number)
            ON DUPLICATE KEY UPDATE status_number = NEW.status_number;
            
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    END IF;
END
$$
DELIMITER ;

-- delete from RmStatusRule;
-- 
-- 
-- INSERT INTO RmStatusRule (
--     date_start, date_end, rm_number, 
--     status_number, notes
-- ) VALUES (
--     CURDATE(), CURDATE() + INTERVAL 3 DAY, 101,
--     2, '房间维修'
-- );
-- 
-- UPDATE RmStatusRule
-- SET date_end = '2025-07-20'
-- WHERE rmstatusrule_id = 10;

-- 每日价格是改的，不能能删，订单是改的，留着，不能删，但是房间会有删除的情况
-- 房型规则删除前触发器
DROP TRIGGER IF EXISTS droprr_before;
DELIMITER $$
CREATE TRIGGER droprr_before BEFORE DELETE ON RmStatusRule FOR EACH ROW
BEGIN
    DECLARE v_start_date DATE;
    DECLARE v_end_date DATE;

    -- 只处理当前日期及未来14天内的日期
    SET v_start_date = GREATEST(OLD.date_start, CURDATE());
    SET v_end_date = LEAST(OLD.date_end, CURDATE() + INTERVAL 13 DAY);
    
    -- 规则作用于房型
    IF OLD.rmtype_id IS NOT NULL THEN
        -- 删除该房型下所有房间在规则日期范围内的记录
        DELETE FROM RmDailyStatus
        WHERE ddate BETWEEN v_start_date AND v_end_date
          AND rm_number IN (SELECT rm_number FROM Room WHERE roomtype_id = OLD.rmtype_id);
        
    -- 规则作用于房间
    ELSEIF OLD.rm_number IS NOT NULL THEN
        -- 删除该房间在规则日期范围内的记录
        DELETE FROM RmDailyStatus
        WHERE ddate BETWEEN v_start_date AND v_end_date
          AND rm_number = OLD.rm_number;
    END IF;
END
$$
DELIMITER ;

-- delete from RmStatusRule;
-- 
-- -- 2. 创建房型规则
-- INSERT INTO RmStatusRule (date_start, date_end, rmtype_id, status_number, notes) 
-- VALUES (CURDATE(), CURDATE() + INTERVAL 5 DAY, 1, 2, '标准间维修');
-- 
-- delete from RmStatusRule where rmstatusrule_id=13;
-- 
-- 
-- -- 3. 创建房间规则
-- INSERT INTO RmStatusRule (date_start, date_end, rm_number, status_number, notes) 
-- VALUES (CURDATE() + INTERVAL 1 DAY, CURDATE() + INTERVAL 3 DAY, 201, 4, '201房打扫');
-- 


