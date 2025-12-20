package com.example.demo.service;

import java.util.*;
import com.example.demo.model.SkillCategory;

public interface SkillCategoryService {
  
    SkillCategory createCategory(SkillCategory category);

    SkillCategory updateCategory(Long id, SkillCategory category);

    SkillCategory getCategoryById(Long id);

    List<SkillCategory> getAllCategories();

    void deactivateCategory(Long id);
}

