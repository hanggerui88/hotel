-- 每天获得价格数据
-- 每天获得价格数据
drop procedure if exists generateroomrates;
delimiter $$

create procedure generateroomrates(
    in start_date date,
    in end_date date,
    in default_rule_id int
)
begin
    declare ddate date default start_date;
    
    -- 创建临时表存储日期序列
    create temporary table if not exists dateseries (d date primary key);
    
    -- 使用循环生成日期序列
    while ddate <= end_date do
        insert ignore into dateseries (d) values (ddate);
        set ddate = date_add(ddate, interval 1 day);
    end while;
    
    -- 为每个房间和日期生成价格记录
    insert ignore into rmtypedailyrate (ddate, rm_number, raterule_id)
    select 
        ds.d,
        r.rm_number,
        coalesce(
            (
                select raterule_id 
                from raterule 
                where rm_number = r.rm_number 
                  and ds.d between date_start and date_end
                order by raterule_id desc
                limit 1
            ),
            (
                select raterule_id 
                from raterule 
                where rmtype_id = r.roomtype_id 
                  and ds.d between date_start and date_end
                order by raterule_id desc
                limit 1
            ),
            default_rule_id
        ) as rule_id
    from dateseries ds
    cross join room r;
    
    -- 清理临时表
    drop temporary table if exists dateseries;
end
$$

delimiter ;

-- 确保事件调度器开启
set global event_scheduler = on;

drop event if exists update_room_rates_daily;
-- 创建定时事件，每天凌晨两点生成价格
delimiter $$
create event if not exists update_room_rates_daily
on schedule every 1 day
starts current_date + interval 1 day + interval 2 hour
do
begin
    -- 获取默认规则id
    set @default_rule_id = (
        select raterule_id 
        from raterule 
        where name = '无折扣' 
        limit 1
    );
    
    -- 生成未来14天的价格记录
    call generateroomrates(
        curdate(),                      -- 今天
        curdate() + interval 13 day,    -- 14天后
        @default_rule_id
    );
end 
$$
delimiter ;

-- 计算最终价格的函数
drop function if exists calculatefinalprice;
delimiter $$

create function calculatefinalprice(
    v_rm_number int,
    v_raterule_id int
) returns decimal(10,2)
deterministic
begin
    declare room_rate decimal(10,2);
    declare formula_type varchar(20);
    declare discount_val decimal(10,2);
    declare condition_val decimal(10,2);
    declare final_price decimal(10,2);
    declare room_found tinyint default 0;
    declare rule_found tinyint default 0;
    
    -- 通过房间号获取基础价格
    select count(*) into room_found from room where rm_number = v_rm_number;
    
    if room_found > 0 then
        select rt.rate into room_rate
        from room r
        join roomtype rt on r.roomtype_id = rt.rmtype_id
        where r.rm_number = v_rm_number
        limit 1;
    else
        set room_rate = 0;  -- 默认值
    end if;
    
    -- 获取折扣规则详情
    select count(*) into rule_found from raterule where raterule_id = v_raterule_id;
    
    if rule_found > 0 then
        select 
            formula,
            discount_value,
            condition_value
        into 
            formula_type,
            discount_val,
            condition_val
        from raterule
        where raterule_id = v_raterule_id
        limit 1;
    else
        -- 默认值：无折扣
        set formula_type = 0;
        set discount_val = 0;
        set condition_val = 0;
    end if;
--     无 0
-- 折扣率 1
-- 固定加价 2
-- 满减 3
-- 固定减 4
    -- 根据折扣类型计算最终价格
    case formula_type
        when '1' then
            set final_price = room_rate * discount_val;
        when '2' then
            set final_price = room_rate + discount_val;
        when '4' then
            set final_price = room_rate - discount_val;
        when '3' then
            if room_rate >= condition_val then
                set final_price = room_rate - discount_val;
            else
                set final_price = room_rate;
            end if;
        else
            set final_price = room_rate;  -- 默认返回基础价格
    end case;
    
    -- 确保价格不为负
    if final_price < 0 then
        set final_price = 0;
    end if;
    
    return final_price;
end
$$
delimiter ;

-- 每日价格视图
drop view if exists vw_dailyroomrate;
create view vw_dailyroomrate as
select 
    rdr.ddate,
    rdr.rm_number,
    r.name as room_name,
    rt.name as room_type,
    rr.name as rate_rule_name,
    rr.formula as discount_type,
    rr.discount_value,
    rt.rate as base_price,
    calculatefinalprice(rdr.rm_number, rdr.raterule_id) as final_price
from rmtypedailyrate rdr
join room r on rdr.rm_number = r.rm_number
join roomtype rt on r.roomtype_id = rt.rmtype_id
join raterule rr on rdr.raterule_id = rr.raterule_id;

select * from vw_dailyroomrate;