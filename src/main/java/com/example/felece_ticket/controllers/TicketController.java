package com.example.felece_ticket.controllers;

import com.example.felece_ticket.Services.TicketService;
import com.example.felece_ticket.models.Ticket;
import com.example.felece_ticket.models.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.getTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/findAllTicketsByUser/{user_id}")
    public ResponseEntity<List<Ticket>> getAllTicketsByUser(@PathVariable("user_id")String user_id){
        List<Ticket> tickets = ticketService.findAllTicketsByUser(user_id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/findAllTicketsByTrain/{user_id}")
    public ResponseEntity<List<Ticket>> getAllTicketsByTrain(@PathVariable("user_id")String train_id){
        List<Ticket> tickets = ticketService.findAllTicketsByTrain(train_id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id")String id){
        Ticket ticket = ticketService.findbyid(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket){
        String tmp=ticketService.add_ticket(ticket);
        return new ResponseEntity<>(tmp,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTicket(@RequestBody Ticket ticket, String user_id, String train_id, String seat_number, Boolean is_bought, Boolean is_cancelled, Boolean is_delayed){
        String tmp=ticketService.Update(ticket,user_id,train_id,seat_number,is_bought,is_cancelled,is_delayed);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @PutMapping("/updateCancelled")
    public ResponseEntity<String> Cancelled(@RequestBody Ticket ticket){
        String tmp=ticketService.cancelled(ticket);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @PutMapping("/updateDelayed")
    public ResponseEntity<String> delayed(@RequestBody Ticket ticket){
        String tmp=ticketService.delayed(ticket);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @PutMapping("/updateSold")
    public ResponseEntity<String> updateSold(@RequestBody Ticket ticket){
        String tmp=ticketService.sold(ticket);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @PutMapping("/updateReserved")
    public ResponseEntity<String> updateReserved(@RequestBody Ticket ticket){
        String tmp=ticketService.reserved(ticket);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

}
