package com.in28minutes.rest.webservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;


    // GET
    // retrieveAllUsers

    @GetMapping(path = "/users")
    public List<User> retriveAllUuers(){
      return service.findAll();
    }

    //GET /users/{id}
    @GetMapping(path = "/users/{id}")
    public User retriveAllUuers(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Validated @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //DELET /users/{id}
    @DeleteMapping(path = "/users/{id}")
    public User deleteUser(@PathVariable int id){
        User user = service.deleteUserById(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

}

