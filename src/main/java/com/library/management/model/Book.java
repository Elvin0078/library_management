package com.library.management.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private Long id;
    private String name;
    private String author;
    private String language;
    private int  noCopiesActual;
    private int  noCopiesCurrent;
    private BookCategory bookCategory;
    private int publicationyear;

}
