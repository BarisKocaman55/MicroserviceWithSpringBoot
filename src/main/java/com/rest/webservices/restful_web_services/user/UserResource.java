package com.rest.webservices.restful_web_services.user;

import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public User findOne(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User not found..." + id);
        }

        return user;
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable  int id) {
        service.deleteByIdVol2(id);
    }
}
