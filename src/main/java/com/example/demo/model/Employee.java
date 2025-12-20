package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String department;
    private String jobTitle;

    private Boolean active;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeSkill> employeeSkills;

    public Employee() {}

    public Employee(String fullName, String email, String department,
                    String jobTitle, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.active = active;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.active == null) this.active = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<EmployeeSkill> getEmployeeSkills() { return employeeSkills; }
    public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) { this.employeeSkills = employeeSkills; }
}
