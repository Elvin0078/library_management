package com.library.management.service.Inter;

import com.library.management.model.User;
import com.library.management.model.UserRole;

import java.util.List;

public interface UserService {

    public User loginUser (String username, String password);

    User getUserByLogin(String username);

    boolean addUser (User user) ;

    boolean deleteUser(Long userId);

    boolean updateUser (User user);

    List<User> getUserList() ;

    List<UserRole>userRoleList();

    User getUserById(Long userId);

    List<User>getUserSearch(String keyword);

}

