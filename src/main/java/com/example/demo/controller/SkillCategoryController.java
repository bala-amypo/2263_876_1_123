package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

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
        return ser.createCate(category);
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody SkillCategory skl) {

    Optional<SkillCategory> e = ser.getCategoryById(id);

    if (e.isPresent()) {
        skl.setId(id);              // ensure update, not new insert
        ser.createCategory(skl);    // save updated data
        return "SkillCategory Updated Successfully";
    } else {
        throw new ResourceNotFoundException("SkillCategory not found");
    }
}


    @GetMapping("/{id}")
    public Optional<SkillCategory> getCateById(@PathVariable Long id) {
        return ser.getCateById(id);
    }

    @GetMapping("/")
    public List<SkillCategory> getAllCate() {
        return ser.getAllCate();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {

        Optional<SkillCategory> e = ser.getCateById(id);

        if (e.isPresent()) {
            SkillCategory skl = e.get();
            skl.setActive(false);
            ser.createCate(skl);
            
        }

    }
}
