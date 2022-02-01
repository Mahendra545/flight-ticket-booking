package com.flightticket.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;
import com.flightticket.repository.UserRepository;
import com.flightticket.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    

    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
  

    public UserDetails loadUserByUsername(String username)  {
    	logger.info("Getting the user details");
        Optional<User> userDetails = userRepository.findByUserName(username);
        if(userDetails.isPresent()){
        	User user = userDetails.get();
        	return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
        }else {
        	logger.error("Invalid username or password");
			throw new InvalidUserCredentials("Invalid username or password.");
        }
        
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
    	logger.info("Getting the user Role");
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
      Optional<User> user = userRepository.findByUserName(username);
      if(user.isPresent()) {
    	  return user.get();
      }else {
      return null;
      }
    }

   
}
