package com.example.demo.model;
import java.sql.Timestamp;
import org.hibernate.annotations.*;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;   // UNIQUE

    private String department;

    private String jobTitle;

    private Boolean active = true;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

 public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getFullName() {
    return fullName;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}

public String getJobTitle() {
    return jobTitle;
}

public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
}

public Boolean getActive() {
    return active;
}

public void setActive(Boolean active) {
    this.active = active;
}

public Timestamp getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
}

public Timestamp getUpdatedAt() {
    return updatedAt;
}

public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
}

public Employee(String fullName, String email, String department,
                String jobTitle, Boolean active) {
    this.fullName = fullName;
    this.email = email;
    this.department = department;
    this.jobTitle = jobTitle;
    this.active = active;
}
public Employee(){

}
}

