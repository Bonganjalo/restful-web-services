package com.in28minutes.rest.webservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;


    // GET /users
    @GetMapping(path = "/users")
    public List<User> retriveAllUsers(){
      return service.findAll();
    }

    //GET /users/{id}
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retriveAllUsers());

        model.add(linkToUsers.withRel("all-users"));

        return model;
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity createUser(@Validated @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //DELETE /users/{id}
    @DeleteMapping(path = "/users/{id}")
    public User deleteUser(@PathVariable int id){
        User user = service.deleteUserById(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

}

