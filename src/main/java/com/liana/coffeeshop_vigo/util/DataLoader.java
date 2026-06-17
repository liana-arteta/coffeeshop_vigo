package com.liana.coffeeshop_vigo.util;

import com.liana.coffeeshop_vigo.model.Category;
import com.liana.coffeeshop_vigo.model.Service;
import com.liana.coffeeshop_vigo.repository.CategoryRepository;
import com.liana.coffeeshop_vigo.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;
    public static final  String[] categories = {"Especialidad", "Tradicional", "Pastelería", "Temática", "Restaurante", "Bar", "Co-working", "Librería"};
    public static final  String[] services = {"Wi-Fi", "Enchufes", "Terraza", "Pet-Friendly", "Opciones veganas", "Para llevar", "Climatización", "Personal amable y atento"};


    public DataLoader(CategoryRepository categoryRepository, ServiceRepository serviceRepository){
        this.categoryRepository = categoryRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(String... args){
        if (categoryRepository.count() == 0){
            System.out.println("================ Creating categories ================");

            for (String name : categories){
                Category cat = new Category();
                cat.setName(name);
                categoryRepository.save(cat);
            }
        }

        if (serviceRepository.count() == 0){
            System.out.println("================ Creando servicios para las cafeterías ================");
            for (String type : services){
                Service serv = new Service();
                serv.setType(type);
                serviceRepository.save(serv);
            }
        }


    }
}
