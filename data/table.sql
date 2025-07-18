/*
创建数据库
*/
create
    database if not exists HeartHome;

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
*/
create table if not exists user
(
    userid               varchar(32) not null primary key comment '用户id',
    username             varchar(32) not null comment '用户名',
    password             varchar(32) not null comment '用户密码',
    avatar_url           varchar(255) comment '用户头像路径',
    personal_description varchar(255) comment '个人简介',
    address              varchar(255) comment '用户地址',
    register_time        datetime comment '注册时间/加入时间',
    manager tinyint(1) default 0 comment '是否为管理员 0为普通用户 1为管理员'
);
insert into user
values ('922', 'rong', '922516', '/image/girl.giff', '初始用户', '广西', now(), 1), ('516', 'fang', '922516', '/image/girl.giff', '初始用户', '河南', now(), 1);



/*
好友表
	id(主键)
    用户id
    用户名
    用户头像路径
    在线状态
	最后在线时间
*/

/*
作品表 图片+标题+简短描述+详细描述+时间
	id
    用户id
    作品标题
    简短描述
    作品图像路径  ---> 使用MySQL的JSON数据的形式
    详细描述
    发布时间
*/

/*
作品表 标题+文字内容+时间
	id
    用户id
    作品标题
    文字内容
    发布时间
*/

/*
作品表 图片+发布时间
	id
    用户id
    作品图像路径
    发布时间
*/

/*
点赞表
	id
    用户id
    作品id
    点赞时间
*/

/*
评论表
	id
    用户id
    作品id
    评论时间
*/


/*
消息表 TODO  涉及websocket  只有当两个人开始聊天时才会产生消息
	id (主键),
    conversation_id (外键, 关联 conversations.id), 
    sender_id (外键, 关联 users.id),
    content, 
    created_at, 
    is_read. 
*/


/*
会话表
	id (INT UNSIGNED, PRIMARY KEY, AUTO_INCREMENT): 唯一会话ID。
	当前用户id (INT UNSIGNED, NOT NULL, FK -> users.id): 第一个参与者的用户ID。
	对方用户id (INT UNSIGNED, NOT NULL, FK -> users.id): 第二个参与者的用户ID。约束建议: 添加一个 CHECK 约束 (user1_id < user2_id) 和一个基于 (user1_id, user2_id) 的 UNIQUE 约束，以确保 user1_id 总是较小的那个，并防止用户 A-B 和用户 B-A 这种重复的会话记录。
	会话创建时间 (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): 会话创建时间。
	最新会话创建时间 (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP): 会话最后一次有消息互动的时间，设为自动更新。用于会话列表排序。
    最新消息id (INT UNSIGNED, NULL, FK -> messages.id): 指向该会话中最新一条消息的ID（方便快速查找最后一条消息的内容和时间戳以在列表中显示）。如果还没有消息，可以为NULL。

消息表
	id (INT UNSIGNED, PRIMARY KEY, AUTO_INCREMENT): 唯一消息ID。
	会话id (INT UNSIGNED, NOT NULL, FK -> conversations.id): 该消息属于哪个会话。
	发送者/对方用户id (INT UNSIGNED, NOT NULL, FK -> users.id): 发送该消息的用户ID。
	消息内容 (TEXT, NOT NULL): 消息的具体内容。可以是纯文本，也可以是JSON格式以支持富文本、图片等。
	消息发送时间 (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): 消息发送时间（对应前端的 timestamp）。
    
    会话双方通过建立一个唯一的会话id来指定消息会话归属哪双方
    后期通过websocket和MySQL实现数据的实时性和持久性
*/


/*
登录信息状态(Redis)
*/

/*
推断所需数据表:
基于以上分析，为了支撑这些功能，你可能需要建立以下数据表：
users (用户表):
用途: 存储所有注册用户的信息。
可能字段: id (主键), username (唯一), password_hash, avatar_url, bio (个人简介), location, created_at (注册时间), last_active_at。

posts (作品/帖子表):
用途: 存储用户发布的所有内容，可能是文章或图片集合。
可能字段: id (主键), user_id (外键, 关联 users.id), type ('article', 'photo_set', 'general' 等), title, short_description, content (存储富文本内容), created_at, updated_at.

post_images (作品图片表):
用途: 存储与 posts 表关联的图片信息，因为一个作品可以有多张图 (Publish.vue)。
可能字段: id (主键), post_id (外键, 关联 posts.id), image_url, order (图片顺序, 可选).
替代方案: 如果每个帖子只展示一张封面图，可以在 posts 表加一个 cover_image_url 字段，但这与 Publish.vue 的多图上传功能不完全匹配。

follows (关注/好友关系表):
用途: 存储用户之间的关注关系。
可能字段: follower_id (外键, 关联 users.id), following_id (外键, 关联 users.id), created_at. (可以使用复合主键 (follower_id, following_id))。通过这张表可以查询粉丝列表和关注列表。

likes (点赞表):
用途: 存储用户对作品的点赞记录。
可能字段: user_id (外键, 关联 users.id), post_id (外键, 关联 posts.id), created_at. (可以使用复合主键 (user_id, post_id))。

comments (评论表):
用途: 存储用户对作品的评论。
可能字段: id (主键), post_id (外键, 关联 posts.id), user_id (外键, 关联 users.id), content, created_at, parent_comment_id (外键, 关联 comments.id, 用于实现评论回复, 可选).

conversations (会话表):
用途: Message.vue 看起来像一个会话列表。这张表用来记录用户间的对话关系。
可能字段: id (主键), user1_id (外键, 关联 users.id), user2_id (外键, 关联 users.id), last_message_id (外键, 关联 messages.id, 可选), last_message_timestamp, user1_unread_count, user2_unread_count. (需要确保 user1_id < user2_id 来避免重复记录).

messages (消息表):
用途: 存储具体的聊天消息内容。
可能字段: id (主键), conversation_id (外键, 关联 conversations.id), sender_id (外键, 关联 users.id), content, created_at, is_read.
总结:
根据你提供的项目文件分析，为了实现核心的动态功能，你至少需要大约 8 张 数据库表：
users
posts
post_images
follows
likes
comments
conversations
messages
请注意:
这只是基于前端代码的推断。实际需要的表和字段会根据你的具体业务逻辑、性能需求和未来的扩展性进行调整。
*/