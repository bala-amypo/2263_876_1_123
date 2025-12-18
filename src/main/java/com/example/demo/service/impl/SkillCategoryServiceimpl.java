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
    public SkillCategory createCate(SkillCategory skl) {
        return repo.save(skl);
    }

    @Override
    public List<SkillCategory> getAllCate() {
        return repo.findAll();
    }

    @Override
    public Optional<SkillCategory> getCateById(Long id) {
        return repo.findById(id);
    }
}
