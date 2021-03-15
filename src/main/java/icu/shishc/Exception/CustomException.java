package icu.shishc.Exception;

import org.springframework.http.HttpStatus;

/**
 * @PackageName:icu.shishc.Exception
 * @Date:2021/3/15, 23:44
 * @Auther:ShiShc
 */

public class CustomException extends Exception{
    private String message;
    private HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
