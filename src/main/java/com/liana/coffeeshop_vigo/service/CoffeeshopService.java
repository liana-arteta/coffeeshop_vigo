package com.liana.coffeeshop_vigo.service;

import com.liana.coffeeshop_vigo.model.Category;
import com.liana.coffeeshop_vigo.model.Coffeeshop;
import com.liana.coffeeshop_vigo.repository.CategoryRepository;
import com.liana.coffeeshop_vigo.repository.CoffeeshopRepository;
import com.liana.coffeeshop_vigo.util.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class CoffeeshopService {
    @Autowired
    private CoffeeshopRepository coffeeshopRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Coffeeshop> getAllCoffeeshop(){
        return coffeeshopRepository.findAll();
    }

    public String filterCategoriesByName(String name){
        if ( name == null) return "Tradicional";

        String nameLower = name.toLowerCase();
        for (String c : DataLoader.categories){
            if (nameLower.contains(c.toLowerCase())){
                return c;
            }
        }

        return "Tradicional";
    }

    public String getDataCoffeeshopsVigo(){

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://nominatim.openstreetmap.org/search?q=cafeteria,+Vigo,+Spain&format=json&limit=250";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "MiAppCafeteriasVigo/1.0 (contacto: liana.arteta.heins@gmail.com)");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseJSON = response.getBody();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode coffeeshopList = mapper.readTree(responseJSON);

            int count = 0;

            for (JsonNode node : coffeeshopList){
                Long osmId = node.path("osm_id").asLong();
                String name = node.path("name").asText().trim();
                Double lat = node.path("lat").asDouble();
                Double lon = node.path("lon").asDouble();
                String address = node.path("display_name").asText("Vigo, Galicia");

                if (name.isEmpty()){
                    System.out.println("Skipping elemento sin name");
                    continue;
                }

               if (coffeeshopRepository.existsByOsmId(osmId)){
                   System.out.println("Cafetería existe en mi databse: " + name);
                   continue;
               }
                // new object
                Coffeeshop coffeeshop = new Coffeeshop();
                coffeeshop.setName(name);
                coffeeshop.setLatitude(lat);
                coffeeshop.setLongitude(lon);
                coffeeshop.setAddress(address);
                coffeeshop.setOsmId(osmId);

                String categoryDetected = filterCategoriesByName(name);
                Category categoryBd = categoryRepository.findByName(categoryDetected)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada " +categoryDetected));
                coffeeshop.setCategory(categoryBd);
                coffeeshopRepository.save(coffeeshop);
                count++;
            }
            return "¡Paso 1 completado! Se han guardado " + count + " cafeterías en MySQL.";

        } catch (Exception e){
            e.printStackTrace();
            return "ERROR en la importación " + e.getMessage();

        }
    }

}
