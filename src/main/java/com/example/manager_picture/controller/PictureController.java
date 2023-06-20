package com.example.manager_picture.controller;

import com.example.manager_picture.model.Category;
import com.example.manager_picture.model.Picture;
import com.example.manager_picture.service.ICategoryService;
import com.example.manager_picture.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class PictureController {

    @Autowired
    private IPictureService iPictureService;
    @Autowired
    private ICategoryService iCategoryService;

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
//@GetMapping("/create-product")
//public ResponseEntity<Iterable<Product>> findAllCustomer() {
//    List<Product> customers = (List<Product>) productService.findAll();
//    if (customers.isEmpty()) {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    return new ResponseEntity<>(customers, HttpStatus.OK);
//}

    @PostMapping("/create-picture")
    public ModelAndView saveCustomer(@ModelAttribute("picture") Picture picture) {
        iPictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("/picture/create");
        modelAndView.addObject("picture", new Picture());
        modelAndView.addObject("message", "New picture created successfully");
        return modelAndView;
    }
    @GetMapping("/pictures")
    public ModelAndView listCustomers() {
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