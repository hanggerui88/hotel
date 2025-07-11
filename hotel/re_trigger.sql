DROP TRIGGER IF EXISTS re_add;
DELIMITER $$
CREATE TRIGGER re_add AFTER INSERT ON Reservation FOR EACH ROW
BEGIN
    DECLARE v_ddate DATE;
    DECLARE i INT DEFAULT 1;
    
    SET v_ddate = NEW.cin_date;
    
    WHILE v_ddate < NEW.cout_date DO
        -- 更新每日房间状态
        IF EXISTS (
            SELECT 1 FROM RmDailyStatus 
            WHERE ddate = v_ddate AND rm_number = NEW.rm_num
        ) THEN
            UPDATE RmDailyStatus 
            SET status_number = 1  -- 设置为已售出状态
            WHERE ddate = v_ddate AND rm_number = NEW.rm_num;
        ELSE
            INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
            VALUES (v_ddate, NEW.rm_num, 1);  -- 新记录设置为已售出
        END IF;
        
        SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
    END WHILE;
END
$$
DELIMITER ;

-- -- 创建测试预订
-- INSERT INTO Reservation (
--     cin_date, cout_date, adult_num, child_num,
--     rmtype_id, rm_num, cin_name, cin_phone, create_date, staff_id
-- ) VALUES (
--     '2025-06-29', '2025-07-02', 2, 0,
--     2, 101, '张三', '13800138000', CURDATE(), 1
-- );
-- 
-- -- 检查生成的每日状态
-- SELECT * FROM RmDailyStatus 
-- WHERE rm_number = 101 
-- AND ddate BETWEEN '2025-06-29' AND '2025-07-01';

-- 只保留两周数据，时间过去了，房间状态会自然更新，不需要在设置
-- 但是如果我由原来的预定两天改为预定一天，那多余的这天状态我无法设为5 所以还是需要
DROP TRIGGER IF EXISTS re_pre;
DELIMITER $$
CREATE TRIGGER re_pre BEFORE UPDATE ON Reservation FOR EACH ROW
BEGIN 
    DECLARE v_ddate DATE;
    DECLARE max_date DATE;
    
    -- 只处理当前日期及未来14天内的日期
    SET max_date = CURDATE() + INTERVAL 14 DAY;
    SET v_ddate = GREATEST(OLD.cin_date, CURDATE());
    
    -- 如果预订状态是有效的(1)，才需要恢复房间状态
    IF OLD.re_status = 1 THEN
        -- 遍历原入住到离店期间的每一天（只处理14天内）
        WHILE v_ddate < OLD.cout_date AND v_ddate <= max_date DO
            -- 恢复房间状态（设为5-空房）
            UPDATE RmDailyStatus 
            SET status_number = 5 -- 空房状态
            WHERE ddate = v_ddate 
              AND rm_number = OLD.rm_num;
            
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    END IF;
END
$$
DELIMITER ;
-- 预订更新后 (处理新日期范围)
DROP TRIGGER IF EXISTS re_after;
DELIMITER $$
CREATE TRIGGER re_after AFTER UPDATE ON Reservation FOR EACH ROW
BEGIN
    DECLARE v_ddate DATE;
    DECLARE max_date DATE;
    
    -- 只处理非取消状态的预订
    IF NEW.re_status = 1 THEN
        -- 只处理当前日期及未来14天内的日期
        SET max_date = CURDATE() + INTERVAL 13 DAY;
        SET v_ddate = GREATEST(NEW.cin_date, CURDATE());
        
        -- 遍历新入住到离店期间的每一天（只处理14天内）
        WHILE v_ddate < NEW.cout_date AND v_ddate <= max_date DO
            -- 设置房间状态（1-售出）
            INSERT INTO RmDailyStatus (ddate, rm_number, status_number)
            VALUES (v_ddate, NEW.rm_num, 1)
            ON DUPLICATE KEY UPDATE status_number = 1;
            
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    END IF;
END
$$
DELIMITER ;

-- 取消预定
DROP TRIGGER IF EXISTS re_cancel;
DELIMITER $$
CREATE TRIGGER re_cancel AFTER UPDATE ON Reservation FOR EACH ROW
BEGIN
    DECLARE v_ddate DATE;
    DECLARE max_date DATE;
    
    -- 如果状态从有效变为取消
    IF OLD.re_status = 1 AND NEW.re_status = 0 THEN
        -- 只处理当前日期及未来14天内的日期
        SET max_date = CURDATE() + INTERVAL 13 DAY;
        SET v_ddate = GREATEST(OLD.cin_date, CURDATE());
        
        -- 遍历原入住到离店期间的每一天（只处理14天内）
        WHILE v_ddate < OLD.cout_date AND v_ddate <= max_date DO
            -- 恢复房间状态（设为5-空房）
            UPDATE RmDailyStatus 
            SET status_number = 5 -- 空房状态
            WHERE ddate = v_ddate 
              AND rm_number = OLD.rm_num;
            
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    END IF;
END
$$
DELIMITER ;


-- delete from Reservation;
-- 
-- -- 创建测试预订（有效）
-- INSERT INTO Reservation (
--     cin_date, cout_date, adult_num, child_num,
--     rmtype_id, rm_num, cin_name, cin_phone, create_date, re_status, staff_id
-- ) VALUES (
--     CURDATE(), CURDATE() + INTERVAL 3 DAY, 2, 0,
--     1, 101, '测试用户', '1234567890', CURDATE(), 1, 1
-- );
-- 
-- -- 查看创建的每日状态
-- SELECT * FROM RmDailyStatus 
-- WHERE rm_number = 101
-- ORDER BY ddate;
-- 
-- -- 更新预订信息（保持有效）
-- UPDATE Reservation
-- SET cout_date = CURDATE() + INTERVAL 2 DAY
-- WHERE re_id = LAST_INSERT_ID();
-- 
-- -- 查看更新后的每日状态
-- SELECT * FROM RmDailyStatus 
-- WHERE rm_number = 101
-- ORDER BY ddate;
-- 
-- -- 取消预订
-- UPDATE Reservation
-- SET re_status = 0
-- WHERE re_id = LAST_INSERT_ID();
-- 
-- -- 查看取消后的每日状态
-- SELECT * FROM RmDailyStatus 
-- WHERE rm_number = 101
-- ORDER BY ddate;