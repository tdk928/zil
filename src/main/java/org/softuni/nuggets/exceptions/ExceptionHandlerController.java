package org.softuni.nuggets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@ControllerAdvice
public class ExceptionHandlerController {
    public static final String DEFAULT_ERROR_VIEW = "er0r";

    @ExceptionHandler(value = {Exception.class, ResourceNotFoundException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request,
                                            Exception e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        System.out.println("testttttt");
        mav.addObject("datetime",new Date());
        mav.addObject("exception",e);
        mav.addObject("url",request.getRequestURL());
        return mav;
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleResourceNotFoundException() {
//        System.out.println("sdadadda");
//        return "er0r";
//    }

}


