package com.example.felece_ticket.Complex_Controller;

import com.example.felece_ticket.Complex_Services.Train_ticket_service;
import com.example.felece_ticket.models.Ticket;
import com.example.felece_ticket.models.Train;
import com.example.felece_ticket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/train_ticket")
public class Train_Ticket_Controller {
    private Train_ticket_service train_ticket_service;

    @Autowired
    public Train_Ticket_Controller(Train_ticket_service train_ticket_service){
        this.train_ticket_service=train_ticket_service;
    }

    @PutMapping("/buyTicket")
    public ResponseEntity<String> buyTicket(@RequestBody User user, Train train, String seat_number){
        String tmp=train_ticket_service.buy_ticket(user,train,seat_number);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PutMapping("/reservedTicket")
    public ResponseEntity<String> reservedTicket(@RequestBody User user, Train train, String seat_number){
        String tmp=train_ticket_service.reserved_ticket(user,train,seat_number);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PutMapping("/soldCancelledTicket/{ticket_id}")
    public ResponseEntity<String> soldCancelledTicket(@PathVariable("ticket_id")String ticket_id){
        String tmp=train_ticket_service.sold_cancelled_ticket(ticket_id);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PutMapping("/reservedCancelledTicket/{ticket_id}")
    public ResponseEntity<String> reservedCancelledTicket(@PathVariable("ticket_id")String ticket_id){
        String tmp=train_ticket_service.reserved_cancelled_ticket(ticket_id);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PutMapping("/buyDelayedTicket/{ticket_id}")
    public ResponseEntity<String> buyDelayedTicket(@PathVariable("ticket_id")String ticket_id){
        String tmp=train_ticket_service.buy_delayed_ticket(ticket_id);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PutMapping("/reservedDelayedTicket/{ticket_id}")
    public ResponseEntity<String> reservedDelayedTicket(@PathVariable("ticket_id")String ticket_id){
        String tmp=train_ticket_service.reserved_delayed_ticket(ticket_id);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }



}
