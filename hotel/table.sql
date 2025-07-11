drop database hotel ;
create database hotel;
use hotel;
-- 我看的数据是所有房间，但是满减活动可以操作多个房间或者单个房间，
-- 比如某个房间暖气坏了修不好就比同类型的其他房间便宜

-- 有时间就不用id做索引，有需要不重复的比如标签，也不用id做索引吧，这次就不改了

drop table if exists Staff;
create table Staff(
staff_id int(10) primary key auto_increment ,
password varchar(255) not null ,
username varchar(10) not null,
department varchar(10) not null,
email varchar(30) not null,
qx varchar(10) comment '权限',
notes text comment '备注'
);

-- 2
drop table if exists Tag;
create table  Tag(
tag_id int(10) primary key auto_increment ,
content varchar(255) unique);

drop table if exists RoomType;
create table RoomType(
rmtype_id int(10) primary key auto_increment ,
name varchar(255) not null unique,
rate int(20) default 0 comment '房间类型的价格',
notes text comment '备注')engine=innodb;

-- 3
drop table if exists Room;
create table Room(
rm_number int(10) PRIMARY KEY comment'房间号',
name varchar(80) not null,
image varchar(255)not null default '无',
comment_content text,
pf varchar(10) default '无' comment'评分',
pj varchar(20) default '无' comment'评价',
hj varchar(10) default '无' comment'环境',
ws varchar(10) default '无' comment'卫生',
fw varchar(10) default '无' comment'服务',
ss varchar(10) default '无' comment'设施',
roomtype_id int(10),
tag_id int(10) ,
notes text,
foreign key (roomtype_id) references roomtype(rmtype_id) on delete CASCADE
 )engine=innodb;

-- 不用rmtype_id，都设为0
-- 取消时间只能按房间类性设定
drop table if exists RateRule;
create table RateRule(
raterule_id int(10) primary key auto_increment,
date_start date not null comment'规则生效日期',
date_end date not null comment '规则失效日期',
rmtype_id int(10) default null comment '操作一类房间', 
rm_number int(10) default null comment'操作一个房间',
  mon TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周一',
  tue TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周二',
  wed TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周三',
  thu TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周四',
  fri TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周五',
  sat TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周六',
  sun TINYINT(1) DEFAULT 0 NOT NULL COMMENT '周日',
name VARCHAR(50) NOT NULL COMMENT '折扣类型名称',
formula VARCHAR(255) NOT NULL COMMENT '计算公式',
discount_value decimal(5,2) DEFAULT NULL COMMENT '折扣值',
-- 精确存储 decimal
condition_value decimal(5,2) DEFAULT NULL COMMENT '条件值（用于满减等）',
		
canceltime int(2) default 0 comment '入住前两天/前一天可以取消 cin_time-canceltime xx前可取消',
notes text,
key raterule(raterule_id),
key roomtype(rmtype_id),
-- 如果是null可以不用有必须对应的值
foreign key (rmtype_id) references RoomType(rmtype_id) on delete cascade,
foreign key (rm_number) references Room(rm_number) on delete cascade
 )engine=innodb;


-- 单个房间价格记录
drop table if exists RmtypeDailyRate;
create table RmtypeDailyRate(
rmtypedailyrate_id int(10) primary key auto_increment,
ddate date not null,
rm_number int(10) not null,
raterule_id int(10)  not null,-- 关联价格规则id
key RmtypeDailyRate(ddate),
foreign key (rm_number) references Room(rm_number) on delete cascade,
foreign key(raterule_id) references RateRule(raterule_id
) on delete cascade)engine=innodb;
-- cascade 级联删除，规则被删了，对应房间数据都被删

-- 6 
-- 6 
drop table if exists RmStatusRule;
create table RmStatusRule(
    rmstatusrule_id int(10) primary key auto_increment,
    date_start date not null,
    date_end date not null,
    rmtype_id int(10) default null comment '操作一类房间',
    rm_number int(10) default null comment'操作一个房间',
    status_number int(10) default 0 comment '状态：0-默认,1-售出,2-维修,3-纠纷停售,4-打扫,5-空房,6-取消预订', 
    notes text,  
    foreign key (rmtype_id) references RoomType(rmtype_id) on delete cascade,
    foreign key (rm_number) references Room(rm_number) on delete cascade
) engine=innodb;


-- 8单个房间状态记录
drop table if exists RmDailyStatus;
create table RmDailyStatus(
rmdailystatus_id int(10) primary key auto_increment,
ddate date not null,
rm_number int(10) not null,
status_number int(10) default 0 comment '状态：0-默认,1-售出,2-维修,3-纠纷停售,4-打扫,5-空房,6-取消预订',
key RmDailyStatus(ddate),
foreign key (rm_number) references Room(rm_number) on delete cascade
)engine=innodb;

-- 7
-- 预定表
drop  table if exists Reservation;
create table Reservation(
re_id int(10) primary key auto_increment,
cin_date date not null ,
cout_date date not null,
adult_num int(10) DEFAULT null,
child_num int(10) DEFAULT null,
rmtype_id int(10) not null comment'一次订一个类',
rm_num int(10) not null,
-- 只有付款人信息，入住用户信息到酒店前台完善
cin_name varchar(10) not null,
cin_phone varchar(20) not null,
create_date date DEFAULT(CURDATE()) comment '订单创建时间',
re_status TINYINT(1) DEFAULT 1 COMMENT '预订状态：1-有效，0-取消',
staff_id int(10)not null comment '负责这个预定的员工id',
note text comment '用户备注')engine=innodb;


ALTER DATABASE hotel
  CHARACTER SET = 'utf8mb4'
  COLLATE = 'utf8mb4_0900_ai_ci';














-- 2. 创建 Tag 表并添加示例数据
INSERT INTO Tag (content) VALUES
('海景'),
('无烟'),
('套房'),
('带阳台'),
('家庭房');

-- 3. 创建 RoomType 表并添加示例数据
INSERT INTO RoomType (name, rate, notes) VALUES
('标准双人间', 300, '25平米，1张双人床'),
('豪华大床房', 500, '40平米，1张大床，海景'),
('家庭套房', 800, '60平米，两室一厅');

-- 4. 创建 Room 表并添加示例数据
INSERT INTO Room (rm_number, name, image, comment_content, pf, pj, hj, ws, fw, ss, roomtype_id, tag_id, notes) VALUES
(101, '标准间101', 'room101.jpg', '干净舒适', '4.5', '好评', '安静', '干净', '好', '齐全', 1, 2, '无烟房'),
(102, '标准间102', 'room102.jpg', '宽敞明亮', '4.6', '非常满意', '安静', '整洁', '热情', '完善', 1, 2, '无烟房'),
(201, '豪华房201', 'room201.jpg', '海景房', '4.8', '非常满意', '优美', '整洁', '优质', '豪华', 2, 1, '海景大床房'),
(202, '豪华房202', 'room202.jpg', '豪华套房', '4.9', '极致体验', '优美', '极佳', '卓越', '顶级', 2, 3, '海景套房'),
(301, '家庭套房301', 'room301.jpg', '温馨舒适', '4.7', '适合家庭', '安静', '干净', '周到', '齐全', 3, 4, '带阳台家庭套房');

-- 5. 创建 RateRule 表并添加示例数据
INSERT INTO RateRule (
    date_start, date_end, rmtype_id, rm_number,
    mon, tue, wed, thu, fri, sat, sun,
    name, formula, discount_value, condition_value, canceltime, notes
) VALUES
-- 标准间平日折扣 (周一至周四)
('2025-01-01', '2025-12-31', 1, NULL,
 1,1,1,1,0,0,0,
 '平日折扣', '折扣率', 0.8, NULL, 1, '提前1天可免费取消'),
 
-- 豪华房周末折扣 (周五至周日)
('2025-01-01', '2025-12-31', 2, NULL,
 0,0,0,0,1,1,1,
 '周末特惠', '折扣率', 0.85, NULL, 2, '提前2天可免费取消'),
 
 
-- 旺季价格上浮
('2025-07-01', '2025-08-31', NULL, NULL,
 1,1,1,1,1,1,1,
 '旺季涨价', '固定加价', 100, NULL, 3, '夏季旺季价格上浮'),
 
-- 满减活动
('2025-07-01', '2025-07-31', NULL, NULL,
 1,1,1,1,1,1,1,
 '五一特惠', '满减', 150, 600, 2, '五一假期满减活动');


-- 7. 创建 RmStatusRule 表并添加示例数据
INSERT INTO RmStatusRule (date_start, date_end, rmtype_id, rm_number, status_number, notes) VALUES
-- 101房间维修状态
('2025-02-01', '2025-02-10', NULL, 101, 2, '暖气维修');



-- 9. 创建 Reservation 表并添加示例数据
INSERT INTO Reservation (
    cin_date, cout_date, adult_num, child_num, 
    rmtype_id, rm_num, cin_name, cin_phone, re_status,
    create_date, staff_id, note
) VALUES
('2025-07-09', '2025-07-12', 2, 0, 
 2, 201, '张先生', '13800138000',1,
 '2025-06-28', 2, '要求高楼层'),
 
('2025-07-09', '2025-07-12', 2, 0, 
 2, 201, '张先生', '13800138000',0,
 '2025-06-29', 2, '要求高楼层'),
 
('2025-07-10', '2025-07-15', 4, 2, 
 3, 301, '王先生', '13700137000',1,
 '2025-07-10', 1, '家庭旅游');