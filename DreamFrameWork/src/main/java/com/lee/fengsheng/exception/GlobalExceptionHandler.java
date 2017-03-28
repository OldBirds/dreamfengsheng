package com.lee.fengsheng.exception;

import com.lee.fengsheng.model.DreamResult;
import com.sun.org.apache.bcel.internal.generic.StackProducer;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by XinSheng on 2016/12/14.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("myerror");
//        return mav;
//    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DreamResult jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        StringBuilder builder = new StringBuilder();
        e.printStackTrace();
        System.out.println("3333333");

//        for(int i=0;i<10;i++){
//            StackTraceElement stackTraceElement =   e.getCause().getStackTrace()[i];
//            builder.append(stackTraceElement.getFileName());
//            builder.append(" ");
//            builder.append(stackTraceElement.getLineNumber());
//            builder.append(" ");
//            builder.append(stackTraceElement.getMethodName());
//        }

        return  DreamResult.instance(false,"请求出1错",builder.toString());
    }

}