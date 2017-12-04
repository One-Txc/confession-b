package com.didispace.mapper;


import com.didispace.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by txc on 17-12-4.
 */
public interface AnnotationUserMapper {
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "id",  column = "id", javaType = Long.class),
            @Result(property = "age", column = "age", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id", javaType = Long.class),
            @Result(property = "age", column = "age", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(id,age,name) VALUES(#{id}, #{age}, #{name})")
    void insert(User user);

    @Update("UPDATE users SET name=#{name},age=#{age} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);
}
