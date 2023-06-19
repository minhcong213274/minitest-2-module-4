package com.example.manager_picture.service;

import com.example.manager_picture.model.Category;
import com.example.manager_picture.model.Picture;
import com.example.manager_picture.repository.IPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class PictureService implements IPictureService {
    @Autowired
    private IPictureRepository iPictureRepository;
    @Override
    public Iterable<Picture> findAll() {
        return iPictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return iPictureRepository.findById(id);
    }

    @Override
    public Picture save(Picture picture) {
        return iPictureRepository.save(picture);
    }

    @Override
    public void remove(Long id) {
        iPictureRepository.deleteById(id);
    }

    @Override
    public Iterable<Picture> findAllByCategory(Category category) {
        return iPictureRepository.findAllByCategory(category);
    }
}
