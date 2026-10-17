# 数据库配置

1. 安装并启动 MySQL 8（MySQL 5.7 也可）。
2. 执行 `src/main/resources/db/schema.sql`，它会创建数据库、表、默认分类和管理员。
3. 当前本机默认连接为 `root`（空密码），可用环境变量覆盖：

```bash
export DB_URL='jdbc:mysql://localhost:3306/no018_specialtypromotion?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai'
export DB_USERNAME='root'
export DB_PASSWORD='root' # 本机 root 无密码时可以不设置
mvn spring-boot:run
```

默认管理员账号为 `admin`，密码为 `admin`。首次登录后请及时修改。

如果使用 IntelliJ IDEA，请在 Run/Debug Configuration 的 Environment variables 中填写同样的三个变量。
