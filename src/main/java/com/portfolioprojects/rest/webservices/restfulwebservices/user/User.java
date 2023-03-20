package com.portfolioprojects.rest.webservices.restfulwebservices.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private int id;
    private String name;
    private LocalDate birthDate;

}