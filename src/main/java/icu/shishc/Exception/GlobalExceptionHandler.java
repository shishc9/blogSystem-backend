package icu.shishc.Exception;

import icu.shishc.dto.MyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PackageName:icu.shishc.Exception
 * @Date:2021/3/15, 23:32
 * @Auther:ShiShc
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> ExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) {
        log.error("GlobalExceptionHandler::ExceptionHandler=> e=\"{}\". Stack is :\n", exception.getMessage(),exception);
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<MyDTO> CustomExceptionHandler(HttpServletRequest httpServletRequest, CustomException customException) {
        MyDTO myDTO = MyDTO.wrongDTO(customException.getHttpStatus(), customException.getMessage());
        log.warn("GlobalExceptionHandler::CustomExceptionHandler=> e=\"{}\".Stack is:\n",customException.getMessage(),customException);
        return new ResponseEntity<MyDTO>(myDTO, HttpStatus.OK);
    }
}
