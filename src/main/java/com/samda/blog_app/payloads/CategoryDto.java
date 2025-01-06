package com.samda.blog_app.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


    private int category_id;
    @NotEmpty(message = "title should not be null")
    @Size(min =3,max=20,message="Size must be atleast 3 and max 10")
    private String title;
    private String categoryDesc;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public @NotEmpty(message = "title should not be null") @Size(min = 3, max = 20, message = "Size must be atleast 3 and max 10") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "title should not be null") @Size(min = 3, max = 20, message = "Size must be atleast 3 and max 10") String title) {
        this.title = title;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
