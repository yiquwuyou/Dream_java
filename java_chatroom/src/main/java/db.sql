create database if not exists java_chatroom charset utf8mb4;

use java_chatroom;

drop table if exists user;
-- create table user (
--     userId int primary key auto_increment,
--     username varchar(20) unique,
--     password varchar(20)
-- );

-- create table user
-- (
--     userId   int primary key auto_increment,
--     username varchar(20) unique,
--     password varchar(20),
--     nickname varchar(100),
--     avatarPath varchar(512) default 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7',
--     signature varchar(1024) default '这个人很懒，什么也没有留下',
--     sex varchar(10) default '草履虫'
-- );

create table user
(
    userId   int primary key auto_increment,
    username varchar(20) unique,
    password varchar(20),
    nickname varchar(100),
    avatarPath varchar(512),
    signature varchar(1024),
    sex varchar(10)
);

-- insert into user values(1, 'zhangsan', '123');
-- insert into user values(2, 'lisi', '123');
-- insert into user values(3, 'wangwu', '123');
-- insert into user values(4, 'zhaoliu', '123');

insert into user values(1, 'zhangsan', '123', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7', '这个人很懒，什么都没有留下', '草履虫');
insert into user values(2, 'lisi', '123', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7', '这个人很懒，什么都没有留下', '草履虫');
insert into user values(3, 'wangwu', '123', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7', '这个人很懒，什么都没有留下', '草履虫');
insert into user values(4, 'zhaoliu', '123', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7', '这个人很懒，什么都没有留下', '草履虫');
insert into user values(5, 'wangqingzhe', '123', '小王要努力', 'https://img.gexingshuo.com/up/31/bc/8a/31bc8a8e1ac7419531fc195898a63335.jpeg', '我王庆哲就喜欢学习', '男');

-- 创建好友表
drop table if exists friend;
create table friend (
    userId int,
    friendId int
);

insert into friend values(1,2);
insert into friend values(2,1);
insert into friend values(1,3);
insert into friend values(3,1);
insert into friend values(1,4);
insert into friend values(4,1);

-- 创建会话表
drop table if exists message_session;
create table message_session (
    sessionId int primary key auto_increment,
    -- 上次访问时间
    lastTime datetime
);

insert into message_session values(1, '2000-05-01 00:00:00');
insert into message_session values(2, '2000-06-01 00:00:00');

-- 创建会话和用户的关联表
drop table if exists message_session_user;
create table message_session_user(
    sessionId int,
    userId int
);

-- 1 号会话里有张三和李四
insert into message_session_user values(1,1),(1,2);
-- 1 号会话里有张三和王五
insert into message_session_user values(2,1),(2,3);

-- 创建消息表
drop table if exists message;
create table message (
    messageId int primary key auto_increment,
    fromId int, -- 消息是属于哪个用户发送的
    sessionId int,  -- 消息属于哪个会话      这里理清楚，不是用户发给另一个用户，而是用户发给会话，为群聊做准备
    content varchar(2048),  -- 消息的正文
    postTime datetime       -- 消息的发送时间
);

-- 构造几个消息数据, 方便测试
-- 张三和李四发的消息
insert into message values (1, 1, 1, '今晚吃啥', '2000-05-01 17:00:00');
insert into message values (2, 2, 1, '随便', '2000-05-01 17:01:00');
insert into message values (3, 1, 1, '那吃面?', '2000-05-01 17:02:00');
insert into message values (4, 2, 1, '不想吃', '2000-05-01 17:03:00');
insert into message values (5, 1, 1, '那你想吃啥', '2000-05-01 17:04:00');
insert into message values (6, 2, 1, '随便', '2000-05-01 17:05:00');
insert into message values (11, 1, 1, '那吃米饭炒菜?', '2000-05-01 17:06:00');
insert into message values (8, 2, 1, '不想吃', '2000-05-01 17:07:00');
insert into message values (9, 1, 1, '那你想吃啥?', '2000-05-01 17:08:00');
insert into message values (10, 2, 1, '随便', '2000-05-01 17:09:00');

-- 张三和王五发的消息
insert into message values(7, 1, 2, '晚上一起约?', '2000-05-02 12:00:00');

drop table if exists add_friend;
CREATE TABLE add_friend
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    fromId   INT,
    fromName VARCHAR(128),
    fromNickname varchar(512),
    fromAvatar varchar(512),
    toId     INT,
    toName   VARCHAR(128),
    toNickname varchar(512),
    toAvatar varchar(512),
    requestMessage varchar(512),
    isAgree      INT DEFAULT 2,   -- 1 同意  0 拒绝  2 未同意
    isDeleted    INT  DEFAULT 1,   -- 1 删了  0 没删
    createTime   DATETIME DEFAULT now(),
    updateTime   DATETIME DEFAULT now() ON UPDATE now()
);

insert into add_friend values(null, 1, 'zhangsan', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7',
                              5, 'wangqingzhe', '小王要努力', 'https://img.gexingshuo.com/up/31/bc/8a/31bc8a8e1ac7419531fc195898a63335.jpeg',
                              '你好，我是王庆哲，想和你交个朋友',
                              2, 1, '2000-05-01 17:00:00', '2000-05-01 17:00:00');
insert into user values(5, 'wangqingzhe', '123', '小王要努力', 'https://img.gexingshuo.com/up/31/bc/8a/31bc8a8e1ac7419531fc195898a63335.jpeg', '我王庆哲就喜欢学习', '男');
