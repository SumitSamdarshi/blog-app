package com.samda.blog_app.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    //here we consider email as username
    private String username;


    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}