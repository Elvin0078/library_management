package com.library.management.repository.Impl;

import com.library.management.mapper.UserMapper;
import com.library.management.model.User;
import com.library.management.model.UserRole;
import com.library.management.repository.Inter.UserRepository;
import com.library.management.sql.AppSql;
import com.library.management.util.Md5Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    public final UserMapper userMapper;
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public Md5Generator md5Generatorl;


    @Autowired
    public UserRepositoryImpl(UserMapper userMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate, Md5Generator md5Generatorl) {
        this.userMapper = userMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.md5Generatorl = md5Generatorl;
    }


    @Override
    public List<User> getUserList() throws Exception {

        List<User> userList;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        userList = namedParameterJdbcTemplate.query(AppSql.GET_USER_LIST, mapSqlParameterSource, userMapper::getUserList);


        return userList;
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        User user;
        user = namedParameterJdbcTemplate.query(AppSql.GET_USER_BY_ID, mapSqlParameterSource, userMapper::getUser);
        return user;
    }

    @Override
    public User loginUser(String username, String password) throws Exception {


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        mapSqlParameterSource.addValue("password", password);
        User user = namedParameterJdbcTemplate.query(AppSql.LOGIN_USER, mapSqlParameterSource, userMapper::getUser);

        return user;


    }

    @Override
    public User getUserByLogin(String username) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        User user = namedParameterJdbcTemplate.query(AppSql.GET_USER_BY_LOGIN, mapSqlParameterSource, userMapper::getUser);
        return user;

    }

    @Override
    public boolean addUser(User user) throws Exception {


        if (getUserByLogin(user.getUsername()).getUserId() != null) {
            return false;
        }
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("full_name", user.getFullname());
        mapSqlParameterSource.addValue("username", user.getUsername());
        mapSqlParameterSource.addValue("password", md5Generatorl.generateMd5(user.getPassword()));
        mapSqlParameterSource.addValue("phone", user.getPhone());
        mapSqlParameterSource.addValue("date_of_birthday", user.getDOB());
        mapSqlParameterSource.addValue("user_role_id", user.getUserRole().getRoleId());
        int count = namedParameterJdbcTemplate.update(AppSql.ADD_USER, mapSqlParameterSource);
        return count > 0;


    }

    @Override
    public boolean updateUser(User user) throws Exception {


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("fullname", user.getFullname());
        mapSqlParameterSource.addValue("username", user.getUsername());
        mapSqlParameterSource.addValue("password", md5Generatorl.generateMd5(user.getPassword()));
        mapSqlParameterSource.addValue("userRoleId", user.getUserRole().getRoleId());
        mapSqlParameterSource.addValue("phone", user.getPhone());
        mapSqlParameterSource.addValue("dob", user.getDOB());
        mapSqlParameterSource.addValue("userId", user.getUserId());

        int count = namedParameterJdbcTemplate.update(AppSql.UPDATE_USER, mapSqlParameterSource);

        return count > 0;


    }

    @Override
    public boolean deleteUser(Long userId) throws Exception {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        int count = namedParameterJdbcTemplate.update(AppSql.DELETE_USER, mapSqlParameterSource);
        return count > 0;

    }

    @Override
    public List<UserRole> userRoleList() throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List<UserRole> userRoleList = namedParameterJdbcTemplate.query(AppSql.GET_USER_ROLE_LIST, mapSqlParameterSource, userMapper::getUserRoleList);
        return userRoleList;
    }

    @Override
    public List<User> getUserSearch(String keyword) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("keyword", keyword);
        List<User> userList = namedParameterJdbcTemplate.query(AppSql.GET_USERS_SEARCH, mapSqlParameterSource, userMapper::getUserList);
        return userList;
    }
}











