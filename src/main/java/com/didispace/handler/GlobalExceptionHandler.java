package com.didispace.handler;

import com.alibaba.fastjson.JSON;
import com.didispace.exception.MyException;
import com.didispace.util.WebUtil;
import com.didispace.util.model.ResultData;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 统一的controller层异常处理：实现对.ajax和普通的http请求返回不同的类型数据
 * Created by txc on 17-12-3.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = Logger.getLogger(getClass());

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse rep, Exception e) throws Exception {
        logger.error(e,e);
        rep.addHeader("Content-Type","application/json;charset=UTF-8");
        PrintWriter out = rep.getWriter();
        out.println(JSON.toJSONString(ResultData.fail(e.getMessage())));
        out.flush();
        out.close();
        return null;
    }

//    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
//    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, HttpServletResponse rep, MyException e) throws Exception {
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ErrorInfo.ERROR);
//        r.setData("Some Data");
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }
}