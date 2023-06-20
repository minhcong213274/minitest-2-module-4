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
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IPictureService iPictureService;

    @GetMapping("/category")
    public ModelAndView listCategory() {
        Iterable<Category> categorys = iCategoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categorys", categorys);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
//    @GetMapping("/create-producers")
//    public ResponseEntity<Iterable<Producer>> findAllCustomer() {
//        List<Producer> customers = (List<Producer>) producerService.findAll();
//        if (customers.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }

    @PostMapping("/create-category")
    public ModelAndView saveProvince(@ModelAttribute("category") Category category) {
        iCategoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Category> category = iCategoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateProvince(@ModelAttribute("category") Category category) {
        iCategoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> category = iCategoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-category")
    public ModelAndView deleteProvince(@ModelAttribute("category") Category category) {
        iCategoryService.remove(category.getCategoryId());
        Iterable<Category> categorys = iCategoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categorys", categorys);
        return modelAndView;
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = iCategoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("/error.404");
        }

        Iterable<Picture> pictures = iPictureService.findAllByCategory(categoryOptional.get());

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", categoryOptional.get());
        modelAndView.addObject("pictures", pictures);
        return modelAndView;
    }

}
