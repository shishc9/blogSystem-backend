# 博客系统后端

## 如何运行

1. 安装JDK

2. 修改 `src/main/resources/application.yml` 里的数据源及其它配置

3. 安装数据库，MySQL或PostgreSQL
    - 如果使用MySQL，执行 `src/main/resources/sql/` 里的 `blog-backend.sql` 和 `blog-backend-init.sql` 脚本（大概）
    - 如果使用PostgreSQL，在命令行输入 `psql -h 你的数据库地址 -U 数据库用户名 -d postgres` ，并执行以下命令

        ```psql
        \i src/main/resources/sql/pg-backend.sql
        \i src/main/resources/sql/pg-backend-init.sql
        ```

4. 在命令行输入以下命令

    ```sh
    mvn spring-boot:run
    ```

## 文档

<https://github.com/shishc9/blogSystem-backend/wiki>
