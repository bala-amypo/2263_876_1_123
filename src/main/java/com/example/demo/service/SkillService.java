package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);

    List<Skill> getAllSkills();

    Optional<Skill> getSkillById(Long id);

    void deactivateSkill(Long id);
}
