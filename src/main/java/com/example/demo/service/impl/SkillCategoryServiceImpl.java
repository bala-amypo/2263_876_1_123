package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryServiceImpl(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {

        Optional<SkillCategory> existing =
                skillCategoryRepository.findByCategoryName(category.getCategoryName());

        if (existing.isPresent()) {
            return null; 
        }

        return skillCategoryRepository.save(category);
    }

    // UPDATE
    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        category.setId(id);
        return skillCategoryRepository.save(category);
    }

    // GET BY ID
    @Override
    public SkillCategory getCategoryById(Long id) {
        Optional<SkillCategory> cat = skillCategoryRepository.findById(id);

        if (cat.isPresent()) {
            return cat.get();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<SkillCategory> getAllCategories() {
        return skillCategoryRepository.findAll();
    }


    @Override
    public void deactivateCategory(Long id) {
        Optional<SkillCategory> cat = skillCategoryRepository.findById(id);

        if (cat.isPresent()) {
            SkillCategory c = cat.get();
            c.setActive(false);
            skillCategoryRepository.save(c);
        }
    }
}
