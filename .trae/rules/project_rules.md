# HeartHome 项目开发规则

## 1. 项目概述

### 1.1 项目架构
- **项目类型**: Spring Boot 3.4.4 多模块项目
- **Java版本**: Java 21
- **构建工具**: Maven
- **数据库**: MySQL 8.0
- **ORM框架**: MyBatis 3.0.4

### 1.2 模块结构
```
service/
├── heartServer/     # 主服务模块 (Controller, Service, Mapper)
├── heartPojo/       # 数据对象模块 (Entity, VO, DTO)
├── heartCommon/     # 公共模块 (工具类, 异常处理, 结果封装)
└── data/           # 数据库脚本和文档
```

## 2. 代码规范

### 2.1 包命名规范
- **Controller**: `com.rong.heartserver.Controller.{模块名}`
- **Service**: `com.rong.heartserver.Service` 和 `com.rong.heartserver.Service.Impl`
- **Mapper**: `com.rong.heartserver.Mapper`
- **Entity**: `com.rong.heartpojo.Entity`
- **VO**: `com.rong.heartpojo.Vo`
- **DTO**: `com.rong.heartpojo.Dto`

### 2.2 类命名规范
- **Controller**: `{模块名}Controller`
- **Service接口**: `{模块名}Service`
- **Service实现**: `{模块名}ServiceImpl`
- **Mapper**: `{模块名}Mapper`
- **Entity**: `{模块名}Entity`
- **VO**: `{模块名}Vo`
- **DTO**: `{模块名}Dto`

### 2.3 注解使用规范

#### Controller层
```java
@RestController
@RequestMapping("/模块名")
@CrossOrigin("*") // 允许跨域请求
@Slf4j
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
}
```

#### Service层
```java
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void methodName() {
        // 业务逻辑
    }
}
```

#### Mapper层
```java
@Mapper
public interface ModuleMapper {
    @Select("SQL语句")
    ReturnType methodName(ParamType param);
}
```

### 2.4 注释规范

#### 方法注释
```java
/*
 * 方法功能描述
 *
 * @param paramName 参数描述
 * @return 返回值描述
 */
```

#### 日志规范
```java
log.info("Controller---操作描述：{}, {}", param1, param2);
```

## 3. 数据库规范

### 3.1 表命名规范
- 使用小写字母和下划线
- 表名应具有描述性
- 例: `user`, `works`, `follows`

### 3.2 字段命名规范
- 使用小写字母和下划线
- 主键统一使用 `{表名}id`
- 时间字段: `create_time`, `update_time`
- 状态字段: `status` (0正常, 1禁用)

### 3.3 索引规范
- 主键索引: 自动创建
- 普通索引: `idx_{字段名}`
- 复合索引: `idx_{字段1}_{字段2}`

### 3.4 约束规范
- 检查约束: `chk_{约束描述}`
- 外键约束: `fk_{表名}_{字段名}`

## 4. API设计规范

### 4.1 RESTful API规范
- **GET**: 查询操作
- **POST**: 创建操作
- **PUT**: 更新操作
- **DELETE**: 删除操作

### 4.2 URL命名规范
```
/模块名/操作/参数
例:
/user/login/signin
/user/userInfo/{username}
/user/friends/{username}
```

### 4.3 响应格式规范
使用统一的Result封装类:
```java
Result<T> {
    code: 状态码
    message: 消息
    data: 数据
}
```

## 5. 异常处理规范

### 5.1 自定义异常
- 按模块创建异常类
- 继承RuntimeException
- 例: `UserLoginException`, `UserSinUpException`

### 5.2 全局异常处理
- 使用`@ControllerAdvice`统一处理异常
- 位置: `com.rong.heartserver.ExceptionHandler.GlobalExceptionHandler`

## 6. 安全规范

### 6.1 JWT认证
- 使用JWT进行用户认证
- 配置文件: `JwtUtilsYml`
- 拦截器: `AuthInterceptor`

### 6.2 跨域处理
- Controller层统一添加`@CrossOrigin("*")`
- 或通过WebMvcConfigurer全局配置

## 7. 事务管理规范

### 7.1 事务注解
- 在Service层方法上添加`@Transactional`
- 指定回滚异常: `@Transactional(rollbackFor = Exception.class)`

### 7.2 事务边界
- 事务应在Service层控制
- 避免在Controller层开启事务

## 8. SQL编写规范

### 8.1 MyBatis注解
- 简单查询使用`@Select`
- 复杂查询可使用XML映射文件
- SQL语句格式化为单行（项目要求）

### 8.2 参数绑定
- 使用`#{paramName}`进行参数绑定
- 避免字符串拼接，防止SQL注入

### 8.3 查询优化
- 合理使用索引
- 避免SELECT *，明确指定字段
- 复杂查询考虑性能优化

## 9. 依赖管理规范

### 9.1 版本管理
- 在父pom.xml的properties中统一管理版本号
- 使用dependencyManagement统一管理依赖

### 9.2 模块依赖
- heartServer依赖heartPojo和heartCommon
- heartPojo和heartCommon相互独立

## 10. 测试规范

### 10.1 单元测试
- 使用JUnit 5进行单元测试
- 测试类命名: `{被测试类名}Test`
- 测试方法命名: `test{方法名}_{测试场景}`

### 10.2 集成测试
- 使用Spring Boot Test进行集成测试
- 测试数据库操作和API接口

## 11. 日志规范

### 11.1 日志级别
- **ERROR**: 系统错误
- **WARN**: 警告信息
- **INFO**: 关键业务信息
- **DEBUG**: 调试信息

### 11.2 日志格式
- 使用Slf4j + Logback
- 统一日志格式: `"层级---操作描述：参数1, 参数2"`

## 12. 配置管理规范

### 12.1 配置文件
- 使用application.yml进行配置
- 敏感信息使用环境变量或配置中心

### 12.2 配置类
- 使用`@ConfigurationProperties`绑定配置
- 配置类放在Config包下

## 13. 代码提交规范

### 13.1 Git提交信息格式
```
type(scope): subject

type类型:
- feat: 新功能
- fix: 修复bug
- docs: 文档更新
- style: 代码格式调整
- refactor: 重构
- test: 测试相关
- chore: 构建过程或辅助工具的变动
```

### 13.2 分支管理
- **main**: 主分支(开发)

## 14. 性能优化规范

### 14.1 数据库优化
- 合理设计索引
- 避免N+1查询问题
- 使用连接池管理数据库连接

### 14.2 缓存策略
- 对频繁查询的数据进行缓存
- 使用Redis进行分布式缓存

## 15. 部署规范

### 15.1 环境配置
- **dev**: 开发环境
- **test**: 测试环境
- **prod**: 生产环境

### 15.2 打包部署
- 使用Maven进行项目打包
- 生成可执行jar包进行部署
