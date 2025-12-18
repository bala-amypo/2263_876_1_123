package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    SkillService service;

    @PostMapping("/add")
    public Skill createSkill(@RequestBody Skill skill) {
        return service.createSkill(skill);
    }

    @GetMapping("/fetch")
    public List<Skill> fetchAllSkills() {
        return service.fetchAllSkills();
    }

    @GetMapping("/fetch/{id}")
    public Optional<Skill> fetchSkillById(@PathVariable int id) {
        return service.fetchSkillById(id);
    }

    @PutMapping("/update/{id}")
    public String updateSkill(@PathVariable int id, @RequestBody Skill skill) {
        Optional<Skill> existing = service.fetchSkillById(id);

        if (existing.isPresent()) {
            skill.setId(id);
            service.createSkill(skill);
            return "Skill Updated Successfully";
        } else {
            return id + " not found";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable int id) {
        Optional<Skill> existing = service.fetchSkillById(id);

        if (existing.isPresent()) {
            service.deleteSkill(id);
            return "Skill Deleted Successfully";
        } else {
            return id + " not found";
        }
    }
}
