package icu.shishc.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @PackageName:icu.shishc.dto
 * @Date:2021/3/15, 23:15
 * @Auther:ShiShc
 */

@Data
public class MyDTO {
    Integer code;
    String msg;
    Object data;

    public MyDTO(HttpStatus httpStatus, String msg, Object o) {
        this.code = httpStatus.value();
        this.msg = msg;
        this.data = o;
    }

    public static MyDTO successDTO(Object o) {
        return new MyDTO(HttpStatus.OK, "success", o);
    }

    public static MyDTO wrongDTO(HttpStatus httpStatus, String msg) {
        return new MyDTO(httpStatus, msg, null);
    }
}
