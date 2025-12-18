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

    public EmployeeController(SkillCategoryService ser) {
        this.ser = ser;
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee emp) {
        return ser.createEmployee(emp);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Employee emp) {

        Optional<Employee> e = ser.getEmployeeById(id);

        if (e.isPresent()) {
            emp.setId(id);
            ser.createEmployee(emp);
            return "Employee Updated Successfully";
        }

        return "Employee not found";
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return ser.getEmployeeById(id);
    }

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return ser.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {

        Optional<Employee> e = ser.getEmployeeById(id);

        if (e.isPresent()) {
            SkillCategory emp = e.get();
            emp.setActive(false);
            ser.createEmployee(emp);
            return "SkillCategory Deactivated";
        }

        return id+" not found";
    }
}
