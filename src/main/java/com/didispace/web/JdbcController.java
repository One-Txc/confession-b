package com.didispace.web;

import com.didispace.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by txc on 18-1-1.
 */
@RestController
public class JdbcController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/user/{id}")
    public Map<String,Object> getUser(@PathVariable String id){
        Map<String,Object> dataMap = new HashMap<>(3);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id,age,name FROM users WHERE id = ?");
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id_ = resultSet.getLong("id");
                int age = resultSet.getInt("age");
                String name = resultSet.getString("name");

                dataMap.put("id",id_);
                dataMap.put("age",age);
                dataMap.put("name",name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }

    @RequestMapping(path = "/user/add")
    public Map<String,Object> getUser(@RequestBody User user){
        Map<String,Object> dataMap = new HashMap<>(3);

        boolean result = jdbcTemplate.execute("INSERT INTO users(id,age,name) VALUES (?,?,?);", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setLong(1,user.getId());
                preparedStatement.setInt(2,user.getAge());
                preparedStatement.setString(3,user.getName());
                return preparedStatement.executeUpdate() > 0;
            }
        });
        dataMap.put("result",result);
        return dataMap;
    }
}
