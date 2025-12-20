package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SkillCategory;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

	Optional<SkillCategory> findByCategoryName(String categoryName);
}

