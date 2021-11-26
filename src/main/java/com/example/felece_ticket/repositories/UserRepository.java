package com.example.felece_ticket.repositories;

import com.example.felece_ticket.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User,String> {

    @Query("{ 'tel': ?0 }")
    public User findbytel(String tel);

    @Query("{ 'username': ?0 }")
    public User findbyusername(String username);

    @Query("{ 'id': ?0 }")
    public User findbyid(String id);



}
