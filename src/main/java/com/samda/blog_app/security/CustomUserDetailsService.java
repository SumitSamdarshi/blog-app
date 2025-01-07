package com.samda.blog_app.security;

import com.samda.blog_app.entities.User;
import com.samda.blog_app.exceptions.ResourcesNotFoundException;
import com.samda.blog_app.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourcesNotFoundException("User ", " email: "+username, 0));
        return user;
    }

}
