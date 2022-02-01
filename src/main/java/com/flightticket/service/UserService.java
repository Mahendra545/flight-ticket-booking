package com.flightticket.service;

import java.util.List;

import com.flightticket.entity.User;

public interface UserService {
   
    List<User> findAll();
    User findOne(String username);
}
