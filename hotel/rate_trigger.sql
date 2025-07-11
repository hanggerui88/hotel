


DELIMITER $$

DROP TRIGGER IF EXISTS after_raterule_insert;

CREATE TRIGGER after_raterule_insert AFTER INSERT ON RateRule FOR EACH ROW 
BEGIN
    DECLARE v_ddate DATE;
    DECLARE current_weekday VARCHAR(10);
    DECLARE is_applicable_day TINYINT(1);
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE room_num INT;
    
    -- 游标声明（必须在其他语句前）
    DECLARE cur_rooms CURSOR FOR 
        SELECT rm_number 
        FROM Room 
        WHERE roomtype_id = NEW.rmtype_id;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    -- 设置起始日期（最大值为当前日期）
    SET v_ddate = GREATEST(NEW.date_start, CURDATE());
    
    -- 日期处理循环
    WHILE v_ddate <= NEW.date_end DO
        -- 只处理当前日期 + 14天范围内的日期
        IF v_ddate <= CURDATE() + INTERVAL 14 DAY THEN
            -- 获取星期几
            SELECT DAYNAME(v_ddate) INTO current_weekday;

            -- 检查当天是否适用该规则
            SET is_applicable_day = 
                CASE current_weekday
                    WHEN 'Monday' THEN NEW.mon
                    WHEN 'Tuesday' THEN NEW.tue
                    WHEN 'Wednesday' THEN NEW.wed
                    WHEN 'Thursday' THEN NEW.thu
                    WHEN 'Friday' THEN NEW.fri
                    WHEN 'Saturday' THEN NEW.sat
                    WHEN 'Sunday' THEN NEW.sun
                    ELSE 0
                END;
            
            -- 如果是适用日期
            IF is_applicable_day = 1 THEN
                -- 规则类型处理
                IF NEW.rm_number IS NOT NULL THEN
                    INSERT INTO RmtypeDailyRate (ddate, rm_number, raterule_id)
                    VALUES (v_ddate, NEW.rm_number, NEW.raterule_id)
                    ON DUPLICATE KEY UPDATE raterule_id = NEW.raterule_id;

                -- 整个房型规则
                ELSEIF NEW.rmtype_id IS NOT NULL THEN
                    -- 重置状态
                    SET done = FALSE;
                    OPEN cur_rooms;
                    
                    -- 遍历所有房间
                    rooms_loop: LOOP
                        FETCH cur_rooms INTO room_num;
                        IF done THEN
                            LEAVE rooms_loop;
                        END IF;

                        INSERT INTO RmtypeDailyRate (ddate, rm_number, raterule_id)
                        VALUES (v_ddate, room_num, NEW.raterule_id)
                        ON DUPLICATE KEY UPDATE raterule_id = NEW.raterule_id;
                    END LOOP;
                    
                    CLOSE cur_rooms;
                END IF;
            END IF;
        END IF;  -- 结束14天范围检查
        
        -- 处理下一天
        SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
    END WHILE;
END
$$

DELIMITER ;
-- 
-- -- 1. 清除测试数据
-- DELETE FROM RmtypeDailyRate;
-- DELETE FROM RateRule;

-- -- 2. 创建测试规则
-- INSERT INTO RateRule (
--     date_start, date_end, rm_number, 
--     sat, sun,mon, name, formula, discount_value
-- ) VALUES (
--     CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY), 101,
--     1, 1, 1,'测试规则', 'discount', 0.8
-- -- );
-- INSERT INTO RateRule (
--     date_start, date_end, rmtype_id, 
--     sat, sun,mon, name, formula, discount_value
-- ) VALUES (
--     CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY), 1,
--     1, 1, 1,'测试规则', 'discount', 0.8
-- );
-- 
-- -- 3. 获取规则ID
-- SET @rule_id = LAST_INSERT_ID();

-- 4. 检查结果
-- SELECT * FROM RateRule WHERE raterule_id = @rule_id;
-- SELECT * FROM RmtypeDailyRate WHERE raterule_id = @rule_id;
-- 

-- 删除原触发器
DROP TRIGGER IF EXISTS raterule_pre;
DROP TRIGGER IF EXISTS raterule_after;

-- RateRule更新前触发器
DELIMITER $$
CREATE TRIGGER raterule_pre BEFORE UPDATE ON RateRule FOR EACH ROW
BEGIN 
    -- 检查是否存在相关记录
    IF EXISTS (SELECT 1 FROM RmtypeDailyRate WHERE raterule_id = OLD.raterule_id) THEN
        -- 删除与该规则相关的所有每日价格记录
        DELETE FROM RmtypeDailyRate WHERE raterule_id = OLD.raterule_id;
    END IF;
END
$$
DELIMITER ;

-- RateRule更新后触发器
DELIMITER $$
CREATE TRIGGER raterule_after AFTER UPDATE ON RateRule FOR EACH ROW
BEGIN
    DECLARE v_ddate DATE;
    DECLARE v_end_date DATE;  -- 添加结束日期限制
    DECLARE v_rm_number INT(10);
    DECLARE v_done BOOLEAN DEFAULT FALSE;
    DECLARE cur_room CURSOR FOR 
        SELECT rm_number FROM Room WHERE roomtype_id = NEW.rmtype_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;

    -- 计算实际处理的最大日期（当前日期+14天）
    SET v_end_date = LEAST(NEW.date_end, CURDATE() + INTERVAL 13 DAY);
    SET v_ddate = GREATEST(NEW.date_start, CURDATE());  -- 只处理当前及未来日期

    -- 规则作用于特定房间类型
    IF NEW.rmtype_id IS NOT NULL THEN
        OPEN cur_room;
        read_loop: LOOP
            FETCH cur_room INTO v_rm_number;
            IF v_done THEN
                LEAVE read_loop;
            END IF;
            
            -- 仅处理有效日期范围内的数据
            WHILE v_ddate <= v_end_date DO
                -- 根据星期几判断是否适用
                IF (
                    (DAYOFWEEK(v_ddate) = 2 AND NEW.mon = 1) OR
                    (DAYOFWEEK(v_ddate) = 3 AND NEW.tue = 1) OR
                    (DAYOFWEEK(v_ddate) = 4 AND NEW.wed = 1) OR
                    (DAYOFWEEK(v_ddate) = 5 AND NEW.thu = 1) OR
                    (DAYOFWEEK(v_ddate) = 6 AND NEW.fri = 1) OR
                    (DAYOFWEEK(v_ddate) = 7 AND NEW.sat = 1) OR
                    (DAYOFWEEK(v_ddate) = 1 AND NEW.sun = 1)
                ) THEN
                    INSERT INTO RmtypeDailyRate (ddate, rm_number, raterule_id)
                    VALUES (v_ddate, v_rm_number, NEW.raterule_id);
                END IF;
                SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
            END WHILE;
            
            -- 为下一个房间重置日期变量
            SET v_ddate = GREATEST(NEW.date_start, CURDATE());
        END LOOP;
        CLOSE cur_room;
    
    -- 规则作用于特定房间
    ELSEIF NEW.rm_number IS NOT NULL THEN
        -- 仅处理有效日期范围内的数据
        WHILE v_ddate <= v_end_date DO
            IF (
                (DAYOFWEEK(v_ddate) = 2 AND NEW.mon = 1) OR
                (DAYOFWEEK(v_ddate) = 3 AND NEW.tue = 1) OR
                (DAYOFWEEK(v_ddate) = 4 AND NEW.wed = 1) OR
                (DAYOFWEEK(v_ddate) = 5 AND NEW.thu = 1) OR
                (DAYOFWEEK(v_ddate) = 6 AND NEW.fri = 1) OR
                (DAYOFWEEK(v_ddate) = 7 AND NEW.sat = 1) OR
                (DAYOFWEEK(v_ddate) = 1 AND NEW.sun = 1)
            ) THEN
                INSERT INTO RmtypeDailyRate (ddate, rm_number, raterule_id)
                VALUES (v_ddate, NEW.rm_number, NEW.raterule_id);
            END IF;
            SET v_ddate = DATE_ADD(v_ddate, INTERVAL 1 DAY);
        END WHILE;
    END IF;
END
$$
DELIMITER ;

-- DELETE FROM RmtypeDailyRate;
-- DELETE FROM RateRule;
-- 
-- INSERT INTO RateRule (
--     date_start, date_end, rm_number, 
--      sun,mon, name, formula, discount_value
-- ) VALUES (
--     CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY), 101,
--      1, 1,'测试规则', 'discount', 0.8
-- );
-- INSERT INTO RateRule (
--     date_start, date_end, rmtype_id, 
--      sun,mon, name, formula, discount_value
-- ) VALUES (
--     CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY), 2,
--      1, 1,'测试规则', 'discount', 0.8
-- );
-- 
-- -- 更新房间类型规则（增加适用日期）
-- UPDATE RateRule 
-- SET  tue = 1, wed = 1, thu = 1, fri = 1
-- WHERE raterule_id = 12;
-- 
-- -- 更新单个房间规则（扩大日期范围）
-- UPDATE RateRule 
-- SET date_end = '2025-07-21'
-- WHERE raterule_id = 16;
-- 

