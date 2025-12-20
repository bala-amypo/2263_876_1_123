package com.example.demo.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceimpl implements SkillCategoryService {

    @Autowired
    SkillCategoryRepository repo;

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        return repo.save(category);
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public SkillCategory getCategoryById(Long id) {

    if (skillCategoryRepository.findById(id).isPresent()) {
        return skillCategoryRepository.findById(id).get();
    } else {
        throw new ResourceNotFoundException(
                "SkillCategory not found with id: " + id
        );
    }
}

}
