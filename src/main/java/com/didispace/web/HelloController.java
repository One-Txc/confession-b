package com.didispace.web;

import com.didispace.exception.MyException;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {

    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping("/hello")
    public String index() {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        return "Hello World";
    }

    @RequestMapping("/exception")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

}