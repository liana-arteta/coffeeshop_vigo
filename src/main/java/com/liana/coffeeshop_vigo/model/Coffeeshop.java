package com.liana.coffeeshop_vigo.model;

import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name = "coffeeshop")
public class Coffeeshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffeeshop_id")
    private Integer coffeeshopId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private String address;
    private String phone;
    private String website;
    @Column(columnDefinition = "DECIMAL(9,6)")
    private Double latitude;
    @Column(columnDefinition = "DECIMAL(9,6)")
    private Double longitude;
    @Column (name="opening_hours")
    private  String openingHours;
    @Column (length = 100)
    private String neighborhood;
    @Column(name = "osm_id", unique = true)
    private Long osmId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany
    @JoinTable(
            name = "coffeeshop_service",
            joinColumns = @JoinColumn(name = "coffeeshop_id"),
            inverseJoinColumns = @JoinColumn (name = "service_id")
    )
    private Set<Service> services = new java.util.HashSet<>();

    @OneToMany (mappedBy = "coffeeshop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new java.util.HashSet<>();

    // Constructor

    public Coffeeshop(){

    }

    // Getters and Setters

    public Integer getCoffeshopId() { return coffeeshopId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getNeighbordhood() {
        return neighborhood;
    }

    public void setNeighbordhood(String neighbordhood) {
        this.neighborhood = neighbordhood;
    }



    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
