package com.portfolioprojects.rest.webservices.restfulwebservices.user;

import com.portfolioprojects.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
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
    public List<User> retrieveAllUser() {
        return userDAOService.findAll();
    }

    //GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userDAOService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return user;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
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
