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
    public String updateCategory(@PathVariable Long id, @RequestBody SkillCategory category) {

        Optional<SkillCategory> e = ser.getCategoryById(id);

        if (e.isPresent()) {
            skl.setId(id);
            ser.createCate(skl);
            return "SkillCategory Updated Successfully";
        }

        return id+" not found";
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
   public String deactivate(@PathVariable Long id) {

    Optional<SkillCategory> e = ser.getCateById(id);

    if (e.isPresent()) {
        SkillCategory skl = e.get();
        skl.setActive(false);
        ser.createCategory(skl);
        return "SkillCategory Deactivated";
    } else {
        throw new ResourceNotFoundException("SkillCategory not found");
    }
}
}
