package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public EmployeeSkill create(@RequestBody EmployeeSkill mapping) {
        return service.createEmployeeSkill(mapping);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EmployeeSkill update(@PathVariable Long id,
                                @RequestBody EmployeeSkill mapping) {
        return service.updateEmployeeSkill(id, mapping);
    }

    // GET SKILLS FOR EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getSkillsForEmployee(@PathVariable Long employeeId) {
        return service.getSkillsForEmployee(employeeId);
    }

    // GET EMPLOYEES BY SKILL
    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getEmployeesBySkill(@PathVariable Long skillId) {
        return service.getEmployeesBySkill(skillId);
    }

    // DEACTIVATE
    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
    }
}
