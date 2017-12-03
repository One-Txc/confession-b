package com.didispace.web;

import com.didispace.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by txc on 17-12-3.
 */
@RestController
public class RedisTestController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping("/redis")
    public String index() throws Exception{
        logger.debug("stringRedisTemplate == redisTemplate:"+(stringRedisTemplate == redisTemplate));
        stringRedisTemplate.opsForValue().set("aaa", "111");
        logger.debug("aaa:"+stringRedisTemplate.opsForValue().get("aaa"));


        User user=new User().setId(123L).setAge(22).setName("txcxyz");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("txc.xyz", user);
        operations.set("txc.xyz.www", user,12, TimeUnit.SECONDS);
        Thread.sleep(1000);
        redisTemplate.delete("txc.xyz");
        boolean exists=redisTemplate.hasKey("txc.xyz.www");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        return "Hello World";
    }
}
