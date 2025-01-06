package com.samda.blog_app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_id;
    @Column(name = "category_title", nullable = false, length = 100)
    private String title;
    @Column(name = "category_description", nullable = false, length = 100)
    private String categoryDesc;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

}