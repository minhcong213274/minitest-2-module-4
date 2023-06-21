package com.example.manager_picture.controller;

import com.example.manager_picture.model.Category;
import com.example.manager_picture.model.Picture;
import com.example.manager_picture.service.ICategoryService;
import com.example.manager_picture.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PictureController {

    @Autowired
    private IPictureService iPictureService;
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> getCategories() {
        return new  ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Iterable<Picture>> allPicture() {
        return new ResponseEntity<>(iPictureService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<Picture>> searchByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(iPictureService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @PostMapping("/api/picture")
    public ResponseEntity<Picture> createSmartphone(@RequestBody Picture picture) {
        return new ResponseEntity<>(iPictureService.save(picture), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/picture/{id}")
    public ResponseEntity<Picture> deletePicture(@PathVariable Long id) {
        Optional<Picture> pictureOptional = iPictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iPictureService.remove(id);
        return new ResponseEntity<>(pictureOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/api/picture/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Long id) {
        Optional<Picture> pictureOptional = iPictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pictureOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/api/picture/{id}")
    public ResponseEntity<Picture> updatePicture(@PathVariable Long id, @RequestBody Picture picture) {
        Optional<Picture> pictureOptional = iPictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Picture updatedPicture = pictureOptional.get();
        updatedPicture.setName(picture.getName());
        updatedPicture.setHeight(picture.getHeight());
        updatedPicture.setWeight(picture.getWeight());
        updatedPicture.setMaterial(picture.getMaterial());
        updatedPicture.setDescription(picture.getDescription());
        updatedPicture.setPrice(picture.getPrice());
        return new ResponseEntity<>(iPictureService.save(updatedPicture), HttpStatus.OK);
    }


    @ModelAttribute("category")
    public Iterable<Category> category(){
        return iCategoryService.findAll();
    }


    @GetMapping("/create-picture")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/picture/create");
        modelAndView.addObject("picture", new Picture());
        return modelAndView;
    }


    @PostMapping("/create-picture")
    public ModelAndView saveCustomer(@ModelAttribute("picture") Picture picture) {
        iPictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("/picture/create");
        modelAndView.addObject("picture", new Picture());
        modelAndView.addObject("message", "New picture created successfully");
        return modelAndView;
    }
    @PostMapping("/search-picture")
    public ModelAndView listPictureByName(@ModelAttribute("search") String name) {
        ModelAndView modelAndView = new ModelAndView("/picture/list");
        modelAndView.addObject("pictures", iPictureService.findAllByNameContaining(name));
        modelAndView.addObject("message", "New picture created successfully");
        return modelAndView;
    }
    @GetMapping("/pictures")
    public ModelAndView listPicture() {
        ModelAndView modelAndView = new ModelAndView("picture/list");
        modelAndView.addObject("pictures", iPictureService.findAll());
        return modelAndView;
    }
    @GetMapping("/edit-picture/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Picture> picture = iPictureService.findById(id);
        if (picture.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/picture/edit");
            modelAndView.addObject("picture", picture.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-picture")
    public ModelAndView updateCustomer(@ModelAttribute("picture") Picture picture) {
        iPictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("/picture/edit");
        modelAndView.addObject("picture", picture);
        modelAndView.addObject("message", "picture updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-picture/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Picture> picture = iPictureService.findById(id);
        if (picture.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/picture/delete");
            modelAndView.addObject("picture", picture.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-picture")
    public ModelAndView deleteCustomer(@ModelAttribute("picture") Picture picture) {
        iPictureService.remove(picture.getPictureId());
        ModelAndView modelAndView = new ModelAndView("picture/list");
        modelAndView.addObject("pictures", iPictureService.findAll());
        return modelAndView;
    }
}