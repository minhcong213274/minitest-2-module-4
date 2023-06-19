package com.example.manager_picture.repository;

import com.example.manager_picture.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
