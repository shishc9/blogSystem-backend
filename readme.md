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

### `/` 下的API

定义在 `src/main/java/icu/shishc/controller/LoginController.java` 中。

- `login(username: string, pwd: string)`

- `GET index`

- `logout`

- `GET noAuth`

- `register{username: string, password: string, userIdentity: "TOURIST" | "BLOGGER", age: number, gender: string, hobby: string, email: string}`

定义在 `src/main/java/icu/shishc/controller/MyErrorController.java` 中。

- `error`

### `/test/` 下的API

定义在 `src/main/java/icu/shishc/controller/MyTestController.java` 中。

- `GET returnString`

- `GET testString(s: string)`

- `GET testBlog`

- `POST testBody(s: string)`

- `GET test404`

- `GET test-mydto`

### `/blog/` 下的API

定义在 `src/main/java/icu/shishc/controller/BlogController.java` 中。

- `GET get/all`

- `GET get/by-title(title: string)`

- `GET get/by-id(bid: number)`

- `GET get/by-status(blogStatus: "PUBLIC" | "PRIVATE")`

- `GET get/all-like`

- `GET add/like(bid: number)`

- `GET delete/like(bid: number)`

- `GET get/all-read`

- `GET get/previous(bid: number)`

- `GET get/next(bid: number)`

- `add/blog{username: string, title: string, content: stirng, status?: "PUBLIC" | "PRIVATE"}`

- `update/blog{blogId: number, title: string, content: stirng, status?: "PUBLIC" | "PRIVATE"}`

- `delete/blog(bid: number)`

### `/user/` 下的API

定义在 `src/main/java/icu/shishc/controller/UserController.java` 中。

- `GET get/by-username(username: string)`

- `update/user{userId: number, username: string, password: string, age: number, gender: string, hobby: string, email: string}`

- `delete/user(uid: number)`

### `/comment` 下的API
定义在 `src/main/java/icu/shishc/controller/CommentController.java` 中。

- `Get message`
