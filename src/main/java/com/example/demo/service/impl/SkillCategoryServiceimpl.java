package com.example.demo.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceimpl implements SkillCategoryService {

    
    SkillCategoryRepository skillCategoryRepository;
    public SkillCategoryServiceimpl(SkillCategoryRepository skillCategoryRepository){
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        return skillCategoryRepository.save(category);
    }
    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        category.setId(id);
        return skillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory getCategoryById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SkillCategory cat = repo.findById(id).orElse(null);
        if (cat != null) {
            cat.setActive(false);
            repo.save(cat);
        }
    }
}