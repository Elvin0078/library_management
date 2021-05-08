package com.library.management.repository.Inter;

import com.library.management.model.User;
import com.library.management.model.UserRole;

import java.util.List;

public interface UserRepository {

    List<User> getUserList() throws  Exception;

    User getUserById(Long userId) throws Exception;

    User loginUser (String username, String password)throws  Exception;

    User getUserByLogin(String username) throws  Exception;

    boolean addUser (User user) throws  Exception;

    boolean updateUser (User user) throws  Exception;

    boolean deleteUser(Long userId) throws  Exception;

    List<UserRole>userRoleList()throws Exception;

    List<User>getUserSearch(String keyword) throws  Exception;
}
