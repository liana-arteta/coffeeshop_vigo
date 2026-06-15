package com.liana.coffeeshop_vigo.repository;

import com.liana.coffeeshop_vigo.model.Coffeeshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeshopRepository extends JpaRepository<Coffeeshop, Integer> {
}
