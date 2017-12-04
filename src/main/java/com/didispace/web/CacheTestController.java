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
        User user = getAUser(123);
        System.out.println("getUser00");
        return user;
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝77777
     * @param user
     * @return
     */
    @RequestMapping("/getUser01")
    @Cacheable(value="user01-key",key = "#user")
    public User getUser01(User user) {
        user.setName("77777");
        System.out.println(cacheManager.getClass());
        System.out.println("getUser01");
        return user;
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝sss
     * @param user
     * @return
     */
    @RequestMapping("/getUser02")
    @Cacheable(value="user01-key",key = "#user.name")
    public User getUser02(User user) {
        user.setName("777");
        System.out.println(cacheManager.getClass());
        System.out.println("getUser02");
        return user;
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝sss
     * @return
     */
    @RequestMapping("/getUser03")
    @Cacheable(value="user01-key",key = "#name")
    public User getUser03(String name) {
        name = "777";
        System.out.println("getUser03");
        return getAUser(333);
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
        User user = getAUser(111);
        System.out.println("updateUser00");
        return user;
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝sss
     * @return
     */
    @RequestMapping("/updateUser01")
    @CachePut(value="user01-key",key = "#name")
    public User updateUser01(String name) {
        name = "qqq";
        System.out.println("updateUser01");
        return getAUser(222);
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝qqq
     * @param user
     * @return
     */
    @RequestMapping("/updateUser02")
    @CachePut(value="user01-key",key = "#user.name")
    public User updateUser02(User user) {
        user.setName("qqq");
        System.out.println("updateUser02");
        return user;
    }

    /**
     * 假设入参：http:xxx?name=sss
     * key的name＝qqq
     * @param user
     * @return
     */
    @RequestMapping("/updateUser03")
    @CachePut(value="user01-key",key = "#user")
    public User updateUser03(User user) {
        user.setName("qqq");
        System.out.println("updateUser03");
        return user;
    }


    private User getAUser(int xx){
        return User.newAUser(xx);
    }




}
