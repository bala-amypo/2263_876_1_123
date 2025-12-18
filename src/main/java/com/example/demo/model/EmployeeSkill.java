package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Skill skill;

    //  Enum defined inside the entity
    public enum ProficiencyLevel {
        Beginner,
        Intermediate,
        Advanced,
        Expert
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProficiencyLevel proficiencyLevel;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    private Boolean active = true;

    // No-arg constructor
    public EmployeeSkill() {
    }

    // Parameterized constructor
    public EmployeeSkill(Employee employee, Skill skill,
                         ProficiencyLevel proficiencyLevel,
                         Integer yearsOfExperience, Boolean active) {
        this.employee = employee;
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.active = active;
    }
}
