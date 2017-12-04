package com.didispace.web;

import com.didispace.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheTestController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/getUser00")
    @Cacheable(value="user00-key")
    public User getUser00() {
        System.out.println(cacheManager.getClass());
        User user = new User();
        user.setId(123L);
        user.setAge(12);
        user.setName("txc");
        System.out.println("getUser00");
        return user;
    }

    @RequestMapping("/getUser01")
    @Cacheable(value="user01-key",key = "#u")
    public User getUser01(User u) {
        u.setName("777");
        System.out.println(cacheManager.getClass());
        User user = new User();
        user.setId(222L);
        user.setAge(22);
        user.setName("ttxxcc");
        System.out.println("getUser01");
        return user;
    }

    @RequestMapping("/getString")
    @Cacheable(value="string-key")
    public String getString() {
        System.out.println("getString");
        return "string";
    }

    @RequestMapping("/updateUser00")
    @CachePut(value="user01-key")
    public User updateUser00() {
        User user = new User();
        user.setId(888L);
        user.setAge(888);
        user.setName("888");
        System.out.println("updateUser00");
        return user;
    }

    @RequestMapping("/updateUser01")
    @CachePut(value="user01-key",key = "#name")
    public User updateUser01(String name) {
        name = "qqq";
        User user = new User();
        user.setId(666L);
        user.setAge(666);
        user.setName("666");
        System.out.println("updateUser01");
        return user;
    }






}
