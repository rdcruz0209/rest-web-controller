package com.portfolioprojects.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.portfolioprojects.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.portfolioprojects.rest.webservices.restfulwebservices.entity.user.User;
import com.portfolioprojects.rest.webservices.restfulwebservices.service.UserDAOService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserResource {

    private final UserDAOService userDAOService;

    //automatically autowired using constructor - @AllArgsConstructor;

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDAOService.findAll();
    }

    //GET /users/{id}

    //    HATEOAS
//    EntityModel
//    WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userDAOService.findById(id);
        if (user == null) {
//    @UserNotFoundException return status is defined using @ResponseStatus (code = HttpStatus.NOT_FOUND)
            throw new UserNotFoundException("User not found with this id: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        System.out.println(this);
        entityModel.add(link.withRel("all-Users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDAOService.deleteUserById(id);
    }

    //    ResponseEntity handles the response to be sent to the client including the path of the newly created User
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.save(user);
//        we dont want to hardcode the URI being returned so, we use {id}
//        location header - users/4 append id -> /users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
