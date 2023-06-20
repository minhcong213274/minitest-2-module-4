package com.example.manager_picture.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;
    private String name;
    private int height;
    private int weight;
    private String material;
    private String description;
    private int price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "picture_category",joinColumns = @JoinColumn(name = "pictureId"),inverseJoinColumns = @JoinColumn(name = "categoryId"))
    @JsonBackReference
    private Set<Category> category;

    public Picture() {
    }

    public Picture(Long pictureId, String name, int height, int weight, String material, String description, int price) {
        this.pictureId = pictureId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
    }

    public Picture(Long pictureId, String name, int height, int weight, String material, String description, int price, Set<Category> category) {
        this.pictureId = pictureId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
