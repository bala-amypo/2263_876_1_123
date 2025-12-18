package com.example.demo.service.implementation;

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
    public SkillCategory createEmployee(SkillCategory emp) {
        return repo.save(emp);
    }

    @Override
    public List<SkillCategory> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Optional<SkillCategory> getEmployeeById(Long id) {
        return repo.findById(id);
    }
}
