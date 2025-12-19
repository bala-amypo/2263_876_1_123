package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeSkill create(@RequestBody EmployeeSkill es) {
        return service.createEmployeeSkill(es);
    }

    @PutMapping("/{id}")
    public EmployeeSkill update(@PathVariable Long id,
                                @RequestBody EmployeeSkill es) {
        return service.updateEmployeeSkill(id, es);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getByEmployee(@PathVariable Long employeeId) {
        return service.getSkillsForEmployee(employeeId);
    }

    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getBySkill(@PathVariable Long skillId) {
        return service.getEmployeesBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
        return "EmployeeSkill deactivated successfully";
    }
}
