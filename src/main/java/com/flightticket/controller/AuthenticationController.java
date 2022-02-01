package com.flightticket.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightticket.config.JwtTokenUtil;
import com.flightticket.dto.AuthToken;
import com.flightticket.dto.LoginUser;
import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;
import com.flightticket.service.UserService;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    private final Logger logger = LogManager.getLogger(AuthenticationController.class);
    
    @PostMapping(value = "/login")
    public ResponseEntity<AuthToken> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	try {
    		logger.info("Checking the authentication");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getPassword()
                )
        );
        final User user = userService.findOne(loginUser.getUserName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("Generation the JWT Token");
        final String token = jwtTokenUtil.generateToken(authentication);
        logger.info("Sending the JWT Token");
        return new ResponseEntity<>(new AuthToken(token,user.getUserName()),HttpStatus.OK);
    	}catch (AuthenticationException e) {
    		logger.error("Invaild Credentials");
			throw new InvalidUserCredentials("Invaild Credentials");
			
		}
    }


}
