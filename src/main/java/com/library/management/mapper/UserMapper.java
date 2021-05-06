package com.library.management.mapper;

import com.library.management.model.User;
import com.library.management.model.UserRole;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {


    public User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        if (rs.next()) {
            UserRole userRole = new UserRole();
            user.setUserId(rs.getLong("u.User_Id"));
            user.setFullname(rs.getString("u.Full_Name"));
            user.setUsername(rs.getString("u.Username"));
            user.setPassword(rs.getString("u.Password"));
            user.setPhone(rs.getString("u.Phone"));
            user.setDOB(rs.getDate("u.Date_Of_Birthday"));
            user.setRegistrationDate(rs.getDate("u.Registration_Date"));
            userRole.setRoleId(rs.getLong("User_Role_Id"));
            userRole.setRoleName(rs.getString("Role_name"));
            user.setUserRole(userRole);
        }
        return user;

    }


    public List<User> getUserList(ResultSet rs) throws SQLException {

        List<User> userList = new ArrayList();
        while (rs.next()) {
            User user = new User();
            UserRole userRole = new UserRole();
            user.setUserId(rs.getLong("u.User_Id"));
            user.setFullname(rs.getString("u.Full_Name"));
            user.setUsername(rs.getString("u.Username"));
            user.setPhone(rs.getString("u.Phone"));
            user.setDOB(rs.getDate("u.Date_Of_Birthday"));
            user.setRegistrationDate(rs.getDate("u.Registration_Date"));
            userRole.setRoleId(rs.getLong("User_Role_Id"));
            userRole.setRoleName(rs.getString("Role_name"));
            user.setUserRole(userRole);
            userList.add(user);
        }
        return userList;

    }
    public List<UserRole> getUserRoleList(ResultSet rs) throws SQLException{
        List<UserRole> userRoleList= new ArrayList();
        while (rs.next()) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(rs.getLong("Role_Id"));
            userRole.setRoleName(rs.getString("Role_Name"));
            userRoleList.add(userRole);

        }
        return userRoleList;



    }


}
