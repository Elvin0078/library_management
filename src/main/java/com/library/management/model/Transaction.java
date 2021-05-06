package com.library.management.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {

    private Long trId;
    private Book book;
    private User user;
    private Date trDate;
    private Long status;
}
