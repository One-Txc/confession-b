package com.didispace.web;

import com.didispace.domain.User;
import com.didispace.mapper.AnnotationUserMapper;
import com.didispace.mapper.XmlUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by txc on 17-12-4.
 */
@RestController
public class MybatisTestController {
    @Autowired
    private AnnotationUserMapper annotationUserMapper;

    @Autowired
    private XmlUserMapper xmlUserMapper;

    @RequestMapping("mybatis/annotation/add")
    public String add() {
        for(int i=0; i<5; i++){
            annotationUserMapper.insert(User.newAUser(1001+i));
        }
        return "success";
    }

    @RequestMapping("mybatis/annotation/getAll")
    public String getAll() {
        List<User> userList = annotationUserMapper.getAll();
        userList.forEach((user)->{
            System.out.println(user.toString());
        });

        return "success";
    }

    @RequestMapping("mybatis/xml/add")
    public String xmlAdd() {
        for(int i=0; i<5; i++){
            xmlUserMapper.insert(User.newAUser(2001+i));
        }
        return "success";
    }

    @RequestMapping("mybatis/xml/getAll")
    public String xmlGetAll() {
        List<User> userList = xmlUserMapper.getAll();
        userList.forEach((user)->{
            System.out.println(user.toString());
        });
        xmlUserMapper.getAll();

        return "success";
    }

}
