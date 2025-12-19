package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
@Tag(name = "Employee Skills", description = "Employee–Skill mapping APIs")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeSkill createEmployeeSkill(@RequestBody EmployeeSkill es) {
        return service.createEmployeeSkill(es);
    }

    @PutMapping("/{id}")
    public EmployeeSkill updateEmployeeSkill(@PathVariable Long id,
                                             @RequestBody EmployeeSkill es) {
        return service.updateEmployeeSkill(id, es);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getSkillsForEmployee(@PathVariable Long employeeId) {
        return service.getSkillsForEmployee(employeeId);
    }

    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getEmployeesBySkill(@PathVariable Long skillId) {
        return service.getEmployeesBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateEmployeeSkill(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
    }
}
