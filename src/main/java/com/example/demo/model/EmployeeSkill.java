package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    public enum ProficiencyLevel {
        Beginner, Intermediate, Advanced, Expert
    }

    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiencyLevel;

    private Integer yearsOfExperience;

    private Boolean active = true;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Employee getEmployee() {
    return employee;
}

public void setEmployee(Employee employee) {
    this.employee = employee;
}

public Skill getSkill() {
    return skill;
}

public void setSkill(Skill skill) {
    this.skill = skill;
}

public ProficiencyLevel getProficiencyLevel() {
    return proficiencyLevel;
}

public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
    this.proficiencyLevel = proficiencyLevel;
}

public Integer getYearsOfExperience() {
    return yearsOfExperience;
}

public void setYearsOfExperience(Integer yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
}

public Boolean getActive() {
    return active;
}

public void setActive(Boolean active) {
    this.active = active;
}

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
