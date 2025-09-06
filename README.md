# HeartHome Service

### 这里是心灵之所的后端部分

---

## 1. 项目概述

HeartHome 是一个社交媒体平台，用户可以在这里分享他们的想法、图片和作品，并与其他用户互动。该项目是 HeartHome 的后端服务，采用 Spring Boot 3.4.4 构建，并使用 MySQL 8.0 作为数据库。

---

## 2. 技术栈

- **后端框架**: Spring Boot 3.4.4
- **数据库**: MySQL 8.0
- **ORM 框架**: MyBatis 3.0.4
- **构建工具**: Maven
- **Java 版本**: 21

---

## 3. 模块结构

项目采用多模块架构，以实现高内聚、低耦合的设计。

```
service/
├── heartServer/     # 主服务模块 (Controller, Service, Mapper)
├── heartPojo/       # 数据对象模块 (Entity, VO, DTO)
├── heartCommon/     # 公共模块 (工具类, 异常处理, 结果封装)
└── data/           # 数据库脚本和文档
```

- **heartServer**: 包含项目的核心业务逻辑，如 Controller、Service 和 Mapper 层。
- **heartPojo**: 定义了项目的数据实体、视图对象和数据传输对象。
- **heartCommon**: 存放了项目通用的工具类、全局异常处理和统一的结果封装。
- **data**: 包含了数据库的 SQL 脚本和相关文档。

---

## 4. 数据库设计

项目的数据库包含了以下几个核心表：

- **user**: 存储用户基本信息，如用户名、密码、头像等。
- **works**: 存储用户发布的作品，包括图片、标题和描述。
- **follows**: 记录用户之间的关注关系。
- **likes**: 记录用户对作品的点赞。
- **comments**: 存储用户对作品的评论。
- **conversations**: 存储用户之间的会话信息。
- **messages**: 存储用户之间的私信消息。

详细的表结构请参考 `data/Table.sql` 文件。

---

## 5. API 端点

项目遵循 RESTful API 设计规范，主要端点如下：

- **用户管理**:
    - `POST /user/login/signin`: 用户登录
    - `POST /user/register`: 用户注册
    - `GET /user/userInfo/{username}`: 获取用户信息
    - `PUT /user/update`: 更新用户信息
- **作品管理**:
    - `POST /works/create`: 创建新作品
    - `GET /works/{work_id}`: 获取作品详情
    - `GET /works/user/{user_id}`: 获取用户发布的所有作品
    - `DELETE /works/{work_id}`: 删除作品
- **社交互动**:
    - `POST /follows/follow`: 关注用户
    - `POST /likes/like`: 点赞作品
    - `POST /comments/comment`: 发表评论

---

## 6. 快速开始

1. **克隆项目**:
   ```bash
   git clone https://github.com/your-username/HeartHome_Service.git
   ```
2. **配置数据库**:
   - 在 MySQL 中创建名为 `HeartHome` 的数据库。
   - 运行 `data/Table.sql` 脚本以创建表结构。
3. **修改配置**:
   - 在 `heartServer/src/main/resources/application.yml` 中，修改数据库连接信息。
4. **运行项目**:
   - 在项目根目录下运行 `mvn clean install`。
   - 运行 `heartServer` 模块的 `HeartServerApplication`。

---

## 7. 项目规范

项目遵循详细的开发规范，包括代码风格、API 设计、数据库设计和 Git 提交规范等。详细信息请参考 `.trae/rules/project_rules.md` 文件。