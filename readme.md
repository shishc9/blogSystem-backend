# 博客系统后端

## 如何运行

1. 安装JDK

2. 修改 `src/main/resources/application.yml` 里的数据源及其它配置

3. 安装数据库，MySQL或PostgreSQL
    - 如果使用MySQL，执行 `src/main/resources/sql/` 里的 `blog-backend.sql` 和 `blog-backend-init.sql` 脚本（大概）
    - 如果使用PostgreSQL，在命令行输入 `psql -h 你的数据库地址 -U 数据库用户名 -d postgres` ，并执行以下命令

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

### `/test` 下的API

定义在 `src/main/java/icu/shishc/controller/MyTestController.java` 中。

- `returnString`

- `testString(s: string)`

- `testBlog`

- `test404`

- `test-mydto`

### `/blogbackend/blog/` 下的API

定义在 `src/main/java/icu/shishc/controller/BlogController.java` 中。

- `get/all`

- `get/by-title(title: string)`

- `get/by-id(bid: number)`

- `get/by-status(blogStatus: "PUBLIC" | "PRIVATE")`

- `get/all-like`

- `get/all-read`

- `add{username: string, title: string, content: stirng, status?: "PUBLIC" | "PRIVATE"}`

- `update{blogId: number, title: string, content: stirng, status?: "PUBLIC" | "PRIVATE"}`

- `delete(bid: number)`

### `/blogbankend/user/` 下的API

定义在 `src/main/java/icu/shishc/controller/UserController.java` 中。

- `get-by-id(userId: number)`

- `get-by-username(username: string)`

- `insert{username: string, password: string, userIdentity: "TOURIST" | "BLOGGER", age: number, gender: string, hobby: string, email: string}`

- `update{userId: number, username: string, password: string, age: number, gender: string, hobby: string, email: string}`

- `delete(uid: number)`

### `/shiro/` 下的API

定义在 `src/main/java/icu/shishc/controller/ShiroTestController.java` 中。

- `testLogin(userId: string, password: string)`

- `testIndex`
