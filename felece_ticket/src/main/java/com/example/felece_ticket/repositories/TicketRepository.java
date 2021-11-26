package com.example.felece_ticket.repositories;

import com.example.felece_ticket.models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket,String> {
    @Query("{'id': ?0}")
    public Ticket findbyid(String id);

    @Query("{'user_id': ?0}")
    public List<Ticket> findByUserId(String user_id);
    @Query("{'train_id': ?0}")
    public List<Ticket> findByTrainId(String train_id);
}
