package com.guptatyre.service;

import com.guptatyre.entity.Category;
import com.guptatyre.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {

        Optional<Category> existingByName = categoryRepository.findByName(category.getName());

        if (existingByName.isPresent()
                && !existingByName.get().getId().equals(category.getId())) {

            throw new RuntimeException("Category already exists.");

        }

        // New Category
        if (category.getId() == null) {

            return categoryRepository.save(category);

        }

        // Update Existing Category
        Category dbCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        dbCategory.setName(category.getName());
        dbCategory.setDescription(category.getDescription());
        dbCategory.setActive(category.getActive());

        // Notice: createdAt is NOT modified

        return categoryRepository.save(dbCategory);

    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found. "));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
