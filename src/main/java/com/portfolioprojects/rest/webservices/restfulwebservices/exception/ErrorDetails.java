package com.portfolioprojects.rest.webservices.restfulwebservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//template for the customized exception response
@AllArgsConstructor
@Getter
@Setter
@Component
@NoArgsConstructor
public class ErrorDetails {
    LocalDateTime timestamp;
    String message;
    String details;

}
