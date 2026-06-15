package com.liana.coffeeshop_vigo.service;

import com.liana.coffeeshop_vigo.model.Coffeeshop;
import com.liana.coffeeshop_vigo.repository.CoffeeshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeshopService {
    @Autowired
    private CoffeeshopRepository coffeeshopRepository;

    public List<Coffeeshop> getAllCoffeeshop(){
        return coffeeshopRepository.findAll();
    }

}
