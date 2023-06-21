package com.example.manager_picture.repository;

import com.example.manager_picture.model.Category;
import com.example.manager_picture.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPictureRepository extends JpaRepository<Picture, Long> {
    Iterable<Picture> findAllByCategory(Category category);
    Iterable<Picture> findAllByNameContaining(String name);
}
