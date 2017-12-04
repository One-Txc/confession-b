package com.didispace.mapper;

import com.didispace.domain.User;

import java.util.List;

/**
 * Created by txc on 17-12-4.
 */
public interface XmlUserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
