package com.portfolioprojects.rest.webservices.restfulwebservices.controller;

import com.portfolioprojects.rest.webservices.restfulwebservices.entity.user.Post;
import com.portfolioprojects.rest.webservices.restfulwebservices.entity.user.User;
import com.portfolioprojects.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.portfolioprojects.rest.webservices.restfulwebservices.repository.PostRepository;
import com.portfolioprojects.rest.webservices.restfulwebservices.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class UserJpaResource {
    //automatically autowired using constructor - @AllArgsConstructor;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    //GET /users/{id}

    //    HATEOAS
//    EntityModel
//    WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            //    @UserNotFoundException return status is defined using @ResponseStatus (code = HttpStatus.NOT_FOUND)
            throw new UserNotFoundException("User not found with this id: " + id);
        }
        EntityModel<User> entityModel = of(user.get());
        WebMvcLinkBuilder linkToAllUser = linkTo(methodOn(UserJpaResource.class).retrieveAllUsers());
        entityModel.add(linkToAllUser.withRel("all-Users")); //provides the relationship description of the linkToAllUser;

        //check if not empty. if empty, then don't add the link to all posts
        if (!retrievePostsForUser(id).isEmpty()) {
            WebMvcLinkBuilder linkToPost = linkTo(methodOn(UserJpaResource.class).retrievePostsForUser(id));
            entityModel.add(linkToPost.withRel("all-Posts"));
        }
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
//        we dont want to hardcode the URI being returned so, we use {id}
//        location header - users/4 append id -> /users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/entity/users")
    public EntityModel<User> createUserEntityModel(@Valid @RequestBody User user) {
        userRepository.save(user);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(UserJpaResource.class).retrieveUser(user.getId()));
        entityModel.add(link.withRel("link to user")); //provides the relationship description of the link;
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new UserNotFoundException("User not found with this id: " + id);
        }
        userRepository.deleteById(id);
    }

    //    ResponseEntity handles the response to be sent to the client including the path of the newly created User


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> userToRetrievePost = userRepository.findById(id);
        if (userToRetrievePost.isEmpty()) {
            throw new UserNotFoundException("User Not Found with this ID");
        }
        return userToRetrievePost.get().getPosts();
    }

    @GetMapping("/jpa/users/{id}/posts/{postid}")
    public Post retrieveOnePostForUser(@PathVariable int id, @PathVariable int postid) {
        Optional<User> userToRetrievePost = userRepository.findById(id);
        if (userToRetrievePost.isEmpty()) {
            throw new UserNotFoundException("User Not Found with this ID");
        }
        return userToRetrievePost.get().getPostById(postid);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> userToCreatePost = userRepository.findById(id);
        if (userToCreatePost.isEmpty()) {
            throw new UserNotFoundException("User Not Found with this ID");
        }
        post.setUser(userToCreatePost.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
