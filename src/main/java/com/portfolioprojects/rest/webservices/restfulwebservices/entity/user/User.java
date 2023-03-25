package com.portfolioprojects.rest.webservices.restfulwebservices.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolioprojects.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.ToString;
import org.hibernate.grammars.hql.HqlParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@NoArgsConstructor
//@NoArgsConstructor if this class is to be used as
// autowiring candidate in CustomizedResponseEntityExceptionHandler
@ToString
@Entity(name = "UserDetails")
public class User {

    public User(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    protected User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("user_name")
    @Size(min = 2, max = 100, message = "Please input at least 2 characters for name")
    private String name;

    @JsonProperty("birth_date")
    @PastOrPresent(message = "Future date is not accepted for birthday field")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPostById(int id) {
        Optional<Post> postToGet = posts.stream().filter(post -> post.getId() == id).findFirst();
        if (postToGet.isEmpty()) {
            throw new PostNotFoundException("Post Not Found with this id " + id);
        }
        return postToGet.get();

    }

}
