package com.portfolioprojects.rest.webservices.restfulwebservices.user;

import com.portfolioprojects.rest.webservices.restfulwebservices.UserNotFoundException;
import jakarta.validation.Valid;
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
        User user = userDAOService.findById(id);
        if (user == null) {
//    @UserNotFoundException return status is defined using @ResponseStatus (code = HttpStatus.NOT_FOUND)
            throw new UserNotFoundException("User not found with this id: " + id);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public <T> void deleteUser(@PathVariable int id) {
        userDAOService.deleteUserById(id);
    }


    //    ResponseEntity handles the response to be sent to the client including the path of the newly created User
    @PostMapping("users")
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
