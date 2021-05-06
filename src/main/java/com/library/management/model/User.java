package com.library.management.model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private Long userId;
    private String fullname;
    private String username;
    private String password;
    private String phone;
    private Date DOB;
    private Date registrationDate;
    private UserRole userRole;


}
