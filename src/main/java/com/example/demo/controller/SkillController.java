package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id,
                             @RequestBody Skill skill) {

        skill.setId(id);
        return skillService.createSkill(skill);
    }

    @GetMapping("/{id}")
    public Optional<Skill> getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateSkill(@PathVariable Long id) {

        Optional<Skill> skill = skillService.getSkillById(id);

        if (skill.isPresent()) {
            skillService.deactivateSkill(id);
            return "Skill Deactivated Successfully";
        } else {
            return id + " not found";
        }
    }
}

