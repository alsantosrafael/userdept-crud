package com.rafael.userdept.controllers;

import com.rafael.userdept.entities.User;
import com.rafael.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();

    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        return repository.findById(id).get();

    }

    @PostMapping
    public User insert(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return repository.findById(id).map( oldUser -> {
            oldUser.setName(user.getName());
            oldUser.setDepartment(user.getDepartment());
            oldUser.setEmail(user.getEmail());
            return repository.save(user);
        }).orElseGet(() -> {
            user.setId(id);
            return repository.save(user);
        });
    }
}
