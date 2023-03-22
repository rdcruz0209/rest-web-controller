package com.portfolioprojects.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
//@NoArgsConstructor
//@NoArgsConstructor if this class is to be used as
// autowiring candidate in CustomizedResponseEntityExceptionHandler
@AllArgsConstructor
@ToString
public class User {
    private int id;

    @JsonProperty("user_name")
    @Size(min = 2, max = 100, message = "Please input at least 2 characters for name")
    private String name;

    @JsonProperty("birth_date")
    @PastOrPresent(message = "Future date is not accepted for birthday field")
    private LocalDate birthDate;
}
