package com.github.loafer.examples.user.web;

import com.github.loafer.examples.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaojh
 */
@ControllerAdvice
public class ExceptionProcessorAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse userNotFound(HttpServletRequest request, UserNotFoundException ex){
        String url = request.getRequestURL().toString();
        String message = ex.getClass().getSimpleName() + ":" + ex.getName();
        return new ErrorResponse(url, message);
    }
}
