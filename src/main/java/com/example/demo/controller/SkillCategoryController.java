package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService ser;

    public SkillCategoryController(SkillCategoryService ser) {
        this.ser = ser;
    }

    @PostMapping("/")
    public SkillCategory createCategory(@RequestBody SkillCategory category) {
        return ser.createCategory(category);
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @RequestBody SkillCategory category) {

        SkillCategory existing = ser.getCategoryById(id);

        if (existing != null) {
            ser.updateCategory(id, category);
            return "SkillCategory Updated Successfully";
        } else {
            throw new ResourceNotFoundException("SkillCategory not found");
        }
    }


    @GetMapping("/{id}")
    public SkillCategory getCategoryById(@PathVariable Long id) {

        SkillCategory cat = ser.getCategoryById(id);
    
        if (cat != null) {
            return cat;
        } else {
            throw new ResourceNotFoundException("SkillCategory not found");
        }
    }

    @GetMapping("/")
    public List<SkillCategory> getAllCategories() {
        return ser.getAllCategories();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateCategory(@PathVariable Long id) {

        SkillCategory cat = ser.getCategoryById(id);

        if (cat != null) {
            ser.deactivateCategory(id);
            return "SkillCategory Deactivated";
        } else {
            throw new ResourceNotFoundException("SkillCategory not found");
        }
    }
}
