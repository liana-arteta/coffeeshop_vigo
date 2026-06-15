package com.liana.coffeeshop_vigo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="category_id")
    private Integer categoryId;
    @Column (nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Set<Coffeeshop> coffeeshops = new java.util.HashSet<>();

    // Constructor

    public Category (){

    }

    // Getters and Setters

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Coffeeshop> getCoffeeshops() {
        return coffeeshops;
    }

    public void setCoffeeshops(Set<Coffeeshop> coffeeshops) {
        this.coffeeshops = coffeeshops;
    }


}
