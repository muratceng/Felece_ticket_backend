package com.example.felece_ticket.repositories;

import com.example.felece_ticket.models.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrainRepository extends MongoRepository<Train,String> {

    @Query("{'id': ?0}")
    public Train findbyid(String id);


}
