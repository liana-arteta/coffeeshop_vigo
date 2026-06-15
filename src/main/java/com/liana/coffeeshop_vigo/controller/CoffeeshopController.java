package com.liana.coffeeshop_vigo.controller;

import com.liana.coffeeshop_vigo.model.Coffeeshop;
import com.liana.coffeeshop_vigo.repository.CoffeeshopRepository;
import com.liana.coffeeshop_vigo.service.CoffeeshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coffeeshops")
public class CoffeeshopController {
    @Autowired
    private CoffeeshopService coffeeshopService;

    @GetMapping
    public List<Coffeeshop> listCoffeeshpps (){
        return coffeeshopService.getAllCoffeeshop();
    }
}
