/*
创建数据库
*/
create database if not exists HeartHome;

use HeartHome;

/*
创建表
*/
/*
用户表
用户id(主键)
用户名
密码
用户头像路径
个人简介
用户地址
注册时间(加入时间)
更新时间
用户状态 0为正常 1为禁用
是否为管理员 0为普通用户 1为管理员
*/
create table if not exists user (
    userid varchar(32) not null primary key comment '用户id',
    username varchar(32) not null comment '用户名',
    password varchar(32) not null comment '用户密码',
    avatar_url varchar(255) default '/image/girl.giff' comment '用户头像路径',
    personal_description varchar(255) comment '个人简介',
    address varchar(255) comment '用户地址',
    create_time datetime comment '注册时间/加入时间',
    update_time datetime comment '更新时间',
    status tinyint(1) default 0 comment '用户状态 0为正常 1为禁用',
    manager tinyint(1) default 0 comment '是否为管理员 0为普通用户 1为管理员',
    INDEX idx_username (username),
    INDEX idx_created_at (create_time)
);

insert into
          user
      values (
              '922',
              'rong',
              '922516',
              '/image/manager.jpg',
              '初始用户',
              '广西',
              now(),
              now(),
              0,
              1
          ),
          (
              '516',
              'fang',
              '922516',
              '/image/manager.jpg',
              '初始用户',
              '河南',
              now(),
              now(),
              0,
              1
          );

/*
作品表
*/
CREATE TABLE works (
    work_id VARCHAR(6) PRIMARY KEY DEFAULT(
        CONCAT(
            LPAD(
                FLOOR(RAND() * 1000000),
                6,
                '0'
            )
        )
    ) COMMENT '作品ID',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID', -- 逻辑外键,关联user表
    title VARCHAR(200) COMMENT '作品标题',
    image_url VARCHAR(255) COMMENT '作品图片路径',
    short_desc VARCHAR(500) COMMENT '作品简述',
    work_type ENUM(
        'image_title_desc',
        'title_desc',
        'image_only'
    ) NOT NULL COMMENT '作品类型:1图片标题简述,2仅标题简述,3仅图片',
    status TINYINT(1) DEFAULT 1 COMMENT '状态 1发布 0草稿 -1删除',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at),
    INDEX idx_status (status),
    CONSTRAINT chk_image_title_desc CHECK ( -- 根据作品类型设置字段非空约束
        (
            work_type = 'image_title_desc'
            AND image_url IS NOT NULL
            AND title IS NOT NULL
            AND short_desc IS NOT NULL
        )
        OR (
            work_type = 'title_desc'
            AND title IS NOT NULL
            AND short_desc IS NOT NULL
            AND image_url IS NULL
        )
        OR (
            work_type = 'image_only'
            AND image_url IS NOT NULL
            AND title IS NULL
            AND short_desc IS NULL
        )
    )
);

/*
关注关系表 -- 实现关注、粉丝和好友功能
*/
CREATE TABLE follows (
    follower_id VARCHAR(32) NOT NULL COMMENT '关注者ID', -- 逻辑外键
    following_id VARCHAR(32) NOT NULL COMMENT '被关注者ID', -- 逻辑外键
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (follower_id, following_id),
    INDEX idx_follower (follower_id),
    INDEX idx_following (following_id)
);

/*
点赞表 -- 记录了是 哪个用户 执行了点赞操作
*/
CREATE TABLE likes (
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID', -- 逻辑外键
    work_id VARCHAR(32) NOT NULL COMMENT '作品ID', -- 逻辑外键
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (user_id, work_id),
    INDEX idx_work_id (work_id)
);

/*
评论表
*/
CREATE TABLE comments (
    comment_id VARCHAR(6) PRIMARY KEY DEFAULT(
        CONCAT(
            LPAD(
                FLOOR(RAND() * 1000000),
                6,
                '0'
            )
        )
    ) COMMENT '评论ID',
    work_id VARCHAR(32) NOT NULL COMMENT '作品ID', -- 逻辑外键
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID', -- 逻辑外键
    parent_id VARCHAR(32) DEFAULT NULL COMMENT '父评论ID', -- 逻辑外键
    content TEXT NOT NULL COMMENT '评论内容',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_work_id (work_id),
    INDEX idx_user_id (user_id),
    INDEX idx_parent_id (parent_id)
);

/*
会话表
*/
CREATE TABLE conversations (
    conversation_id VARCHAR(6) PRIMARY KEY DEFAULT(
        CONCAT(
            LPAD(
                FLOOR(RAND() * 1000000),
                6,
                '0'
            )
        )
    ) COMMENT '会话ID',
    user1_id VARCHAR(32) NOT NULL COMMENT '用户1ID', -- 逻辑外键
    user2_id VARCHAR(32) NOT NULL COMMENT '用户2ID', -- 逻辑外键
    last_message_id VARCHAR(32) COMMENT '最后消息ID', -- 逻辑外键
    last_message_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后消息时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_users (user1_id, user2_id),
    INDEX idx_user1 (user1_id),
    INDEX idx_user2 (user2_id)
);

/*
消息表
*/
CREATE TABLE messages (
    message_id VARCHAR(6) PRIMARY KEY DEFAULT(
        CONCAT(
            LPAD(
                FLOOR(RAND() * 1000000),
                6,
                '0'
            )
        )
    ) COMMENT '消息ID',
    conversation_id VARCHAR(32) NOT NULL COMMENT '会话ID', -- 逻辑外键
    sender_id VARCHAR(32) NOT NULL COMMENT '发送者ID', -- 逻辑外键
    content TEXT NOT NULL COMMENT '消息内容',
    is_read TINYINT(1) DEFAULT 0 COMMENT '是否已读',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_conversation_id (conversation_id)
);