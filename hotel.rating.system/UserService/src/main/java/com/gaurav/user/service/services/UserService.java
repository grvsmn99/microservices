package com.gaurav.user.service.services;

import com.gaurav.user.service.entities.User;

import java.util.List;

public interface UserService {

    //Create User
    User save(User user);

    //getAllUsers
    List<User> getAllUsers();

    //getUser
    User getUser(String userId);
    //Delete user
    void deleteUser(String userId);
    //Update User
    User updateUser(User user);
}
