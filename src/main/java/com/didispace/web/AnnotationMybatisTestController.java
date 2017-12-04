package com.didispace.web;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by txc on 17-12-4.
 */
@RestController
public class AnnotationMybatisTestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("mybatis/annotation/add")
    public String add() {
        for(int i=0; i<5; i++){
            userMapper.insert(User.newAUser(1001+i));
        }
        return "success";
    }

    @RequestMapping("mybatis/annotation/getAll")
    public String getAll() {
        List<User> userList = userMapper.getAll();
        userList.forEach((user)->{
            System.out.println(user.toString());
        });

        return "success";
    }

}
