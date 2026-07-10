package com.guptatyre.service;

import com.guptatyre.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category save(Category category);
    Category getById(Long id);

    void delete(Long id);

}
