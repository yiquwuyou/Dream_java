-- 如果不存在则创建数据库 CVResume，字符集设为 utf8mb4
create database if not exists questionnaire charset utf8mb4;

-- 切换到 CVResume 数据库
use questionnaire;

-- 如果存在用户表，则删除
drop table if exists user;

-- 创建用户表
create table user
(
    userId   int primary key auto_increment,          -- 用户id
    username varchar(20) unique,                      -- 用户名/账号
    password varchar(20),                             -- 密码
    phone varchar(20),                                -- 电话
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

-- 插入用户数据
-- 插入用户数据
INSERT INTO `user` (username, password, phone)
VALUES
    ('admin', 'admin', '12345678900'), -- admin用户
    ('zhangsan', '123', '12345678901'),
    ('lisi', '123', '12345678902'),
    ('wangwu', '123', '12345678903'),
    ('zhaoliu', '123', '12345678904'),
    ('qianqi', '123', '12345678905'),
    ('sunba', '123', '12345678906');


drop table if exists userquestion;
-- 用户问卷总览表
create table userquestion
(
    id   int primary key auto_increment,              -- 问卷id
    uuid varchar(50) unique,                          -- 问卷唯一标识
    username varchar(20) ,                      -- 用户名/账号
    title varchar(100),                               -- 问卷标题
    content varchar(5000),                            -- 问卷描述
    is_publish int,                                   -- 是否发布  1-发布   0-未发布
    answer_number int default 0,                                -- 问卷数量
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

-- 为 admin 用户生成 userquestion 内容
INSERT INTO `userquestion` (uuid, username, title, content, is_publish, answer_number)
VALUES
    ('35462789', 'admin', 'Admin的问卷1', '这是管理员admin的问卷1的描述', 1, 10),
    ('a165dfsw', 'admin', 'Admin的问卷2', '这是管理员admin的问卷2的描述', 0, 5);

-- 为 张三 用户生成 userquestion 内容
INSERT INTO `userquestion` (uuid, username, title, content, is_publish, answer_number)
VALUES
    ('zhangsan_uuid_1', 'zhangsan', '张三的问卷1', '这是用户张三的问卷1的描述', 1, 8),
    ('zhangsan_uuid_2', 'zhangsan', '张三的问卷2', '这是用户张三的问卷2的描述', 1, 12);

-- 为 李四 用户生成 userquestion 内容
INSERT INTO `userquestion` (uuid, username, title, content, is_publish, answer_number)
VALUES
    ('lisi_uuid_1', 'lisi', '李四的问卷1', '这是用户李四的问卷1的描述', 0, 3),
    ('lisi_uuid_2', 'lisi', '李四的问卷2', '这是用户李四的问卷2的描述', 1, 7);


drop table if exists notice;
-- 系统公告
create table notice
(
    uuid  varchar(50),              -- 公告id
    title varchar(200),                               -- 公告标题
    content varchar(5000),                            -- 公告内容
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);


-- 生成系统公告
INSERT INTO `notice` (uuid, title, content)
VALUES
    ('12336543', '系统公告1', '这是系统公告1的内容'),
    ('36532661', '系统公告2', '这是系统公告2的内容');


drop table if exists userlog;
-- 日志
create table userlog
(
    id   int primary key auto_increment,              -- 日志id
    username varchar(20) ,                            -- 用户名/账号
    role int,                                         -- 日志角色  1-添加   2-修改  3-删除  4-修改别人  5-自己被需改
    content varchar(500),                            -- 日志内容
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

drop table if exists question;
-- 日志
create table question
(
    id   varchar(20),                               -- 这个选项的uuid
    userquestionid   varchar(20),  -- 问卷id         -- 问卷的uuid
    username varchar(20) ,                            -- 用户名/账号
    title varchar(200),                               -- 标题
    type varchar(20),                                 -- 日志内容
    sort int,                                         -- 排序
    isRequired int,                                         -- 是否必填
    content varchar(500),                            -- 文本框内容
    placeholder varchar(500),                            -- 日志内容
    radio varchar(500),                            -- 日志内容
    radioOptions varchar(500),                            -- 日志内容
    checkList varchar(500),                            -- 日志内容
    checkboxOptions varchar(500),                            -- 日志内容
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

drop table if exists writequestion;

-- 问卷填写
create table writequestion
(
    uuid   varchar(20),                               -- 这个选项的uuid
    userquestionid   varchar(20),  -- 问卷id         -- 问卷的uuid
    originusername varchar(20) ,                      -- 发布者用户名/账号
    writeusername varchar(20) ,                      -- 填写者用户名/账号
    isPublish int,                                   -- 是否发布  1-发布   0-未发布
    title varchar(200),                               -- 标题
    type varchar(20),                                 -- 日志内容
    sort int,                                         -- 排序
    isRequired int,                                    -- 是否必填
    content varchar(500),                            -- 文本框内容
    placeholder varchar(500),                            -- 日志内容
    radio varchar(500),                            -- 日志内容
    radioOptions varchar(500),                            -- 日志内容
    checkList varchar(500),                            -- 日志内容
    checkboxOptions varchar(500),                            -- 日志内容
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);


