package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/")
    public EmployeeSkill createData(@RequestBody EmployeeSkill es) {
        return service.createData(es);
    }

    @PutMapping("/{id}")
    public String updateData(@PathVariable Long id,
                             @RequestBody EmployeeSkill es) {

        Optional<EmployeeSkill> existing = service.fetchById(id);

        if (existing.isPresent()) {
            es.setId(id);
            service.createData(es);
            return "EmployeeSkill updated successfully";
        } else {
            return id + " not found";
        }
    }

    
    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> fetchByEmployee(@PathVariable Long employeeId) {
        return service.fetchByEmployee(employeeId);
    }

    
    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> fetchBySkill(@PathVariable Long skillId) {
        return service.fetchBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {

        Optional<EmployeeSkill> es = service.fetchById(id);

        if (es.isPresent()) {
            es.get().setActive(false);
            service.createData(es.get());
            return "EmployeeSkill deactivated successfully";
        } else {
            return id + " not found";
        }
    }
}
