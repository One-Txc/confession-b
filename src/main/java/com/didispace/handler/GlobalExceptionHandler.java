package com.didispace.handler;

import com.didispace.util.WebUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 同一的controller层异常处理：实现对.ajax和普通的http请求返回不同的类型数据
 * Created by txc on 17-12-3.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse rep, Exception e) throws Exception {

        if(WebUtil.isAjax(req)){
            rep.addHeader("Content-Type","application/json;charset=UTF-8");
            PrintWriter out = rep.getWriter();
            out.println("{\"msg\":\"{0}\"}".replace("{0}",e.getMessage()));
            out.flush();
            out.close();
            return null;
        }else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", req.getRequestURL());
            mav.setViewName("error");
            return mav;
        }
    }

    /*@ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, HttpServletResponse rep, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }*/
}