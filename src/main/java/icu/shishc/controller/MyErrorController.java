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
 */

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public MyDTO handleError (HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 401: {
                return return401();
            }

            case 403: {
                return return403();
            }

            case 404: {
                return return404();
            }

            case 500: {
                return return500();
            }

            default:
                return unKnown();
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private MyDTO return401() {
        return MyDTO.wrongDTO(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    private MyDTO return404() {
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "Bad request");
    }

    private MyDTO return403() {
        return MyDTO.wrongDTO(HttpStatus.FORBIDDEN, "FORBIDDEN");
    }

    private MyDTO return500() {
        return MyDTO.wrongDTO(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
    }

    private MyDTO unKnown() {
        return MyDTO.successDTO("Unknown");
    }

}