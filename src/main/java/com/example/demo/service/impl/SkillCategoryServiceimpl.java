package com.example.demo.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceimpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryServiceimpl(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {

        // Check for uniqueness of categoryName
        SkillCategory existing = skillCategoryRepository.findByCategoryName(category.getCategoryName());
        if (existing != null) {
            throw new IllegalArgumentException("Category name must be unique");
        }

        return skillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {

        // Unwrap Optional using orElse(null)
        SkillCategory existingCategory = skillCategoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("SkillCategory not found");
        }

        // Check uniqueness of categoryName (excluding current record)
        SkillCategory byName = skillCategoryRepository.findByCategoryName(category.getCategoryName());
        if (byName != null && !byName.getId().equals(id)) {
            throw new IllegalArgumentException("Category name must be unique");
        }

        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setActive(category.getActive());

        return skillCategoryRepository.save(existingCategory);
    }

    @Override
    public SkillCategory getCategoryById(Long id) {
        // Unwrap Optional using orElse(null)
        return skillCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SkillCategory cat = skillCategoryRepository.findById(id).orElse(null);
        if (cat != null) {
            cat.setActive(false);
            skillCategoryRepository.save(cat);
        }
    }
}
