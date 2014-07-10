package com.github.loafer.examples.web;

import com.github.loafer.examples.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaojh
 */
@ControllerAdvice
public class ExceptionProcessorAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exception(Exception e){
        ModelAndView mac = new ModelAndView("exception");
        mac.addObject("name", e.getClass().getSimpleName());
        mac.addObject("message", e.getMessage());
        return mac;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse userNotFoundException(HttpServletRequest request, UserNotFoundException ex){
        String url = request.getRequestURL().toString();
        String messge = ex.getClass().getSimpleName() + ":" + ex.getName();
        return new ErrorResponse(url, messge);
    }
}
