drop procedure if exists initializeroomstatus;
delimiter $$

create procedure initializeroomstatus(
    in start_date date,
    in end_date date
)
begin
    declare done boolean default false;
    declare current_rm_number int;
    declare cur cursor for select rm_number from room;
    declare continue handler for not found set done = true;
    
    open cur;
    
    rm_loop: loop
        fetch cur into current_rm_number;
        if done then 
            leave rm_loop;
        end if;
        
        -- 为当前房间插入未来日期的状态
        insert into rmdailystatus (ddate, rm_number, status_number)
        with recursive dateseries as (
            select start_date as ddate
            union all
            select ddate + interval 1 day
            from dateseries
            where ddate < end_date - interval 1 day
        )
        select ds.ddate, current_rm_number, 5
        from dateseries ds
        where not exists (
            select 1 
            from rmdailystatus 
            where ddate = ds.ddate 
            and rm_number = current_rm_number
        );
    end loop;
    
    close cur;
end
$$

delimiter ;

set global event_scheduler = on;

-- delimiter $$
-- 
-- create event if not exists dailyroomstatusupdate
-- on schedule 
--     every 1 day
--     starts concat(curdate() + interval 1 day, ' 02:00:00')
-- on completion preserve
-- do
-- begin
--     declare future_date date;
--     
--     call initializeroomstatus(curdate(), curdate() + interval 14 day);
-- end 
-- $$
-- 
-- delimiter ;

drop event if exists dailyroomstatusupdate;
-- 创建定时事件，每天凌晨两点生成价格
delimiter $$
create event if not exists dailyroomstatusupdate
on schedule every 1 day
starts current_date + interval 1 day + interval 2 hour
do
begin
    call initializeroomstatus(curdate(), curdate() + interval 14 day);
end 
$$
delimiter ;

drop view if exists vw_roomstatusdetails;
create or replace view vw_roomstatusdetails as
select 
    s.ddate ,
    r.rm_number ,
    r.name as rm_name,
    rt.name as rm_type,
    case s.status_number
        when 0 then '默认'
        when 1 then '已售出'
        when 2 then '维修中'
        when 3 then '纠纷停售'
        when 4 then '打扫中'
        when 5 then '空房可用'
        when 6 then '预订取消'
        else '未知状态'
    end as rm_status,
    r.image as image
from rmdailystatus s
join room r on s.rm_number = r.rm_number
join roomtype rt on r.roomtype_id = rt.rmtype_id
where s.ddate >= curdate() and s.ddate <= curdate() + interval 14 day
order by s.ddate, r.rm_number;

select * from vw_roomstatusdetails;