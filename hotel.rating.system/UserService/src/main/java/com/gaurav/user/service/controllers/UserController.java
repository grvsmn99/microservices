package com.gaurav.user.service.controllers;

import com.gaurav.user.service.entities.User;
import com.gaurav.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
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
    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable(name="id") String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //Create fallback method for ratingHotelBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("ratingHotelFallback Fallback is excecuted!!!",ex.getMessage());
        User user = User.builder().about("Its a dummy user!!!")
                .email("dummy@dummymail.com")
                .name("Dummy").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}
