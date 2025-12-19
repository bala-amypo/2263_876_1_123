package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // GET ACTIVE SKILLS FOR EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getSkillsForEmployee(@PathVariable Long employeeId) {
        return service.getSkillsForEmployee(employeeId);
    }

    // GET ACTIVE EMPLOYEES FOR SKILL
    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getEmployeesBySkill(@PathVariable Long skillId) {
        return service.getEmployeesBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
    }
}
