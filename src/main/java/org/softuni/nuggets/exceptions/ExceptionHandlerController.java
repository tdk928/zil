//package org.softuni.nuggets.exceptions;
//
//import org.softuni.nuggets.controllers.BaseController;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//
//@ControllerAdvice
//public class ExceptionHandlerController extends BaseController{
//    public static final String DEFAULT_ERROR_VIEW = "error";
//
////    @ExceptionHandler(value = {Exception.class, ResourceNotFoundException.class})
////    public ModelAndView defaultErrorHandler(HttpServletRequest request,
////                                            Exception e) {
////        ModelAndView mav = new ModelAndView("error-template");
////        System.out.println();
//////        mav.addObject("datetime",new Date());
//////        mav.addObject("exception",e);
//////        mav.addObject("url",request.getRequestURL());
////        return mav;
////    }
//
////    @ExceptionHandler(RuntimeException.class)
////    public ModelAndView getException(RuntimeException e) {
////        String errorMessage =
////                e.getClass().isAnnotationPresent(ResponseStatus.class)
////                        ? e.getClass().getAnnotation(ResponseStatus.class).reason()
////                        : DEFAULT_ERROR_VIEW;
////
////        return this.view("error-template", "error", errorMessage);
////    }
//
//}
//
//
