package com.example.demo.service;

import java.util.*;
import com.example.demo.model.SkillCategory;

public interface SkillCategoryService {
    SkillCategory createCate(SkillCategory skl);
    List<SkillCategory> getAllCate();
    Optional<SkillCategory> getCateById(Long id);
}
