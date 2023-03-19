package com.gaurav.user.service.controllers;

import com.gaurav.user.service.entities.User;
import com.gaurav.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User responseUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    //single user get
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name="id") String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}
