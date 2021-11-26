package com.example.felece_ticket.controllers;

import com.example.felece_ticket.Services.TrainService;
import com.example.felece_ticket.models.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/train")
public class TrainController {
    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService){
        this.trainService=trainService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Train>> getAllTrain(){
        List<Train> trains = trainService.getTrains();
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Train> getUserByUsername(@PathVariable("id")String id){
        Train train = trainService.findbyid(id);
        return new ResponseEntity<>(train, HttpStatus.OK);
    }

    @GetMapping("findbyroute/{departure_id,arrival_id}")
    public ResponseEntity<List<Train>> getAllTrain(@PathVariable("departure_id,arrival_id") String departure_id,String arrival_id){
        List<Train> trains = trainService.findByRoute(departure_id,arrival_id);
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTrain(@RequestBody Train train){
        String tmp= trainService.addTrain(train);
        String tmp2=trainService.new_train(train);
        return new ResponseEntity<>(tmp,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTrain(@RequestBody Train train, String departure_id, String arrival_id, String empty_seats, String reserved_seats, String sold_seats, Date date, int total_num){
        String tmp=trainService.Update(train,departure_id,arrival_id,empty_seats,reserved_seats,sold_seats,date,total_num);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @PutMapping("/updateEmptySeats")
    public ResponseEntity<?> updateEmptySeats(@RequestBody Train train,String seat,Boolean decrase){
        trainService.updateEmptySeats(train,seat,decrase);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateSoldSeats")
    public ResponseEntity<?> updateSoldSeats(@RequestBody Train train,String seat,Boolean decrase){
        trainService.updateSoldSeats(train,seat,decrase);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateReservedSeats")
    public ResponseEntity<?> updateReservedSeats(@RequestBody Train train,String seat,Boolean decrase){
        trainService.updateReservedSeats(train,seat,decrase);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable("id")String id){
        String tmp=trainService.delete(trainService.findbyid(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
