package com.example.felece_ticket.controllers;

import com.example.felece_ticket.Services.CitiesService;
import com.example.felece_ticket.models.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService){
        this.citiesService=citiesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cities>> getAllUser(){
        List<Cities> cities = citiesService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/find/{city_id}")
    public ResponseEntity<Cities> getUserByUsername(@PathVariable("city_id")String city_id){
        Cities city = citiesService.findByCityId(city_id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Cities city){
        String tmp=citiesService.add_city(city);
        return new ResponseEntity<>(tmp,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Cities city,String city_id,String city_name){
        String tmp=citiesService.Update(city,city_id,city_name);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")String id){
        String tmp=citiesService.delete(citiesService.findByCityId(id));
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }
}
