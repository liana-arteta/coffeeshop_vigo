package com.liana.coffeeshop_vigo.repository;

import com.liana.coffeeshop_vigo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
