package com.didispace.web;

import com.didispace.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: txc
 * @date: 18-4-8 下午4:35
 */
@RestController
public class ServletContextController {

    @RequestMapping(value = "/context/{key}")
    public Map getUser00(@PathVariable("key") String key, HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<>(1);
        try {
            ServletContext sc = request.getServletContext();
            String value = sc.getInitParameter(key);
            resultMap.put(key,value);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Value("${spring.profiles.active}")
    private String spring_profiles_active;

    @RequestMapping("/context/test")
    public String sssss() {
        return spring_profiles_active;
    }

}
