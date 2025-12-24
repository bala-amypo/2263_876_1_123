package com.example.demo.dto;

import java.util.List;

public class EmployeeSearchRequest {
    
    private Long userId;
    private List<String> skills;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
