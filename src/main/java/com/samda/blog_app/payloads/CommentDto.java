package com.samda.blog_app.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private int id;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
