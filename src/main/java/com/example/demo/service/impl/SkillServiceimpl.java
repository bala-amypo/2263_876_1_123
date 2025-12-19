package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceimpl implements SkillService {

    
    SkillRepository skillRepository;
    

    @Override
    public Skill createSkill(Skill skill) {
        return repo.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    @Override
    public Optional<Skill> getSkillById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deactivateSkill(Long id) {
        repo.deleteById(id);
    }
}
