package com.example.felece_ticket.repositories;

import com.example.felece_ticket.models.Cities;
import com.example.felece_ticket.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CitiesRepository extends MongoRepository<Cities,String> {

    @Query("{ 'city_id': ?0 }")
    public Cities findByCityId(String city_id);
}

