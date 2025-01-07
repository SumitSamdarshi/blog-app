package com.samda.blog_app.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthResponse {

    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}