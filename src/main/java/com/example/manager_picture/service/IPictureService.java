package com.example.manager_picture.service;


import com.example.manager_picture.model.Category;
import com.example.manager_picture.model.Picture;

public interface IPictureService extends IGeneralService<Picture>{
    Iterable<Picture> findAllByCategory(Category category);
    Iterable<Picture> findAllByNameContaining(String name);
}
