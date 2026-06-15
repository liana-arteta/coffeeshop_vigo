package com.liana.coffeeshop_vigo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "service")
public class Service {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;
    @Column(nullable = false)
    private String type;
    @ManyToMany(mappedBy = "services")
    private Set<Coffeeshop> coffeeshops = new java.util.HashSet<>();

    // Constructor

    public Service(){

    }

    // Getters and Setters

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Coffeeshop> getCoffeeshops() {
        return coffeeshops;
    }

    public void setCoffeeshops(Set<Coffeeshop> coffeeshops) {
        this.coffeeshops = coffeeshops;
    }
}
