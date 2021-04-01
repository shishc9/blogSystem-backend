# 博客系统后端

## 如何运行

1. 安装JDK

2. 修改 `src/main/resources/application.yml` 里的数据源及其它配置

3. 安装数据库，MySQL或PostgreSQL
    - 如果使用MySQL，执行 `src/main/resources/sql/` 里的 `blog-backend.sql` 和 `blog-backend-init.sql` 脚本（大概）
    - 如果使用PostgreSQL，在命令行输入 `psql -h 你的数据库地址 -U 数据库用户名 -d postgres` ，并执行一下命令

        ```psql
        \i src/main/resources/sql/pg-backend.sql
        \i src/main/resources/sql/pg-backend-test.sql
        ```

4. 在命令行输入以下命令

    ```sh
    mvn spring-boot:run
    ```

## API

待补全。

### `/` 下的API

定义在 `src/main/java/icu/shishc/controller/MyTestController.java` 中。

- `add`

- `getbytitle`

- `getAllblog`

- `test`

### `/blogbackend/blog/` 下的API

定义在 `src/main/java/icu/shishc/controller/BlogController.java` 中。

- `getall`

- `getbytitle`

- `getbyid`

- `getbystatus`

- `getalllike`

- `getallread`

- `add`

### `/blogbankend/user/` 下的API

定义在 `src/main/java/icu/shishc/controller/UserController.java` 中。

- `getbyid`

- `getbyname`

### `/shiro/` 下的API

定义在 `src/main/java/icu/shishc/controller/ShiroTestController.java` 中。

- `testLogin`

- `testIndex`
