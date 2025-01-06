package com.samda.blog_app.services;

import java.util.List;

import com.samda.blog_app.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto category, Integer id);
    CategoryDto getUserById(Integer id);
    List<CategoryDto> getAllCategory();
    void deleteCategory(Integer id);
}