package com.portfolioprojects.rest.webservices.restfulwebservices.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Size(min = 10, message = "Please input a post with at least 5 characters")
    private String description;
}
