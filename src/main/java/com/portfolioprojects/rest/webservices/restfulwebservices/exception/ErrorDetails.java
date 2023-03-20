package com.portfolioprojects.rest.webservices.restfulwebservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    /*
     * timestamp
     * errormessage
     * details
     * */
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
