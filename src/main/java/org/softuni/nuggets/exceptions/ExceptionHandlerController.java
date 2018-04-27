package org.softuni.nuggets.exceptions;

import org.softuni.nuggets.controllers.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.softuni.nuggets.areas.contants.Constans.HOME_VIEW;


@ControllerAdvice
public class ExceptionHandlerController extends BaseController{
    public static final String DEFAULT_ERROR_VIEW = "error";

//    @ExceptionHandler(value = {Exception.class, ResourceNotFoundException.class})
//    public ModelAndView defaultErrorHandler(HttpServletRequest request,
//                                            Exception e) {
//        ModelAndView mav = new ModelAndView("error-template");
//        System.out.println();
////        mav.addObject("datetime",new Date());
////        mav.addObject("exception",e);
////        mav.addObject("url",request.getRequestURL());
//        return mav;
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView getException(RuntimeException e) {
//        String errorMessage =
//                e.getClass().isAnnotationPresent(ResponseStatus.class)
//                        ? e.getClass().getAnnotation(ResponseStatus.class).reason()
//                        : DEFAULT_ERROR_VIEW;
//
//        return this.view("error-template", "error", errorMessage);
//    }

}


