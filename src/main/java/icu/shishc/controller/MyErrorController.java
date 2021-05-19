package icu.shishc.controller;

import icu.shishc.dto.MyDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @date: 2021-5-8, 18:09
 * @author: ShiShc
 * @DESC: springboot错误拦截
 */
@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public MyDTO handleError (HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            /* 400 */
            case 400: {
                return return400();
            }

            /* 401 */
            case 401: {
                return return401();
            }

            /* 403 */
            case 403: {
                return return403();
            }

            /* 404 */
            case 404: {
                return return404();
            }

            /* 500 */
            case 500: {
                return return500();
            }

            /* default */
            default:
                return unKnown();
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private MyDTO return400() {
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }

    private MyDTO return401() {
        return MyDTO.wrongDTO(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }

    private MyDTO return404() {
        return MyDTO.wrongDTO(HttpStatus.NOT_FOUND, "NOT_FOUND");
    }

    private MyDTO return403() {
        return MyDTO.wrongDTO(HttpStatus.FORBIDDEN, "FORBIDDEN");
    }

    private MyDTO return500() {
        return MyDTO.wrongDTO(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
    }

    private MyDTO unKnown() {
        return MyDTO.successDTO("UNKNOWN");
    }

}
