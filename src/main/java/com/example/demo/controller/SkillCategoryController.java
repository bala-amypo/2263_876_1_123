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
    public Employee createCate(@RequestBody SkillCategory skl) {
        return ser.createCate(skl);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody SkillCategory skl) {

        Optional<SkillCategory> e = ser.getEmployeeById(id);

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
    public List<SkillCategory> getAllEmployees() {
        return ser.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {

        Optional<SkillCategory> e = ser.getEmployeeById(id);

        if (e.isPresent()) {
            SkillCategory skl = e.get();
            skl.setActive(false);
            ser.createEmployee(emp);
            return "SkillCategory Deactivated";
        }

        return id+" not found";
    }
}
