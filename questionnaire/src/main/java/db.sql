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
    nickname varchar(100),                            -- 昵称
    avatarPath varchar(512),                          -- 头像路径
    signature varchar(1024),                          -- 个性签名
    sex varchar(10),                                  -- 性别
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

-- 插入用户数据
INSERT INTO `user` (userId, username, password, nickname, avatarPath, signature, sex, role)
VALUES (1, 'zhangsan', '123', '点击头像修改昵称哦~', 'https://th.bing.com/th/id/OIP.4or4IzOy3o9ZM2QaX7huXQAAAA?w=211&h=211&c=7&r=0&o=5&pid=1.7',
        '这个人很懒，什么都没有留下', '草履虫', 1);


-- 如果存在用简历表，则删除
drop table if exists resume;

-- 创建简历表
create table resume
(
    id   int primary key auto_increment,          -- 用户id
    username varchar(20) ,                      -- 用户名/账号
    fileName varchar(100),                            -- 文件名
    fileSize int,                                     -- 文件大小(单位字节)
    filePath varchar(512),                            -- 文件路径(目前为网络路径)
    cvRemark varchar(1024),                           -- 简历备注
    status int default 1,                             -- 简历识别状态
    `delete_flag` TINYINT ( 4 ) NULL DEFAULT 1,       -- 删除标志    1 表示启用， 0 表示删除
    `create_time` DATETIME DEFAULT now(),             -- 创建时间
    `update_time` DATETIME DEFAULT now() ON UPDATE now()    -- 更新时间
);

INSERT INTO resume (username, fileName, fileSize, filePath, CVRemark, status)
VALUES ('zhangsan', 'resume.docx', 2048, 'http://quwuyou-1.oss-cn-beijing.aliyuncs.com/05ade4a9-b308-4980-8842-0a329ff9c893.pdf', '优秀候选人', 1);
