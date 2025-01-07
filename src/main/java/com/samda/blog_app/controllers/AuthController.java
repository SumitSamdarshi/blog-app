package com.samda.blog_app.controllers;

import com.samda.blog_app.exceptions.ApiExceptionHandler;
import com.samda.blog_app.payloads.JwtAuthRequest;
import com.samda.blog_app.payloads.JwtAuthResponse;
import com.samda.blog_app.payloads.UserDto;
import com.samda.blog_app.security.JwtTokenHelper;
import com.samda.blog_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")//url is /api/auth/login
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String ourGeneratedToken = this.jwtTokenHelper.generateToken(userDetails);//generateToken takes userDetails
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(ourGeneratedToken);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(usernamePasswordAuthenticationToken+"Samda...");

        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Username or Password!");
            throw new ApiExceptionHandler("Invalid Username or Password!");
        }



    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto){
        UserDto registeredNewUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<UserDto>(registeredNewUser, HttpStatus.CREATED);

    }
}
