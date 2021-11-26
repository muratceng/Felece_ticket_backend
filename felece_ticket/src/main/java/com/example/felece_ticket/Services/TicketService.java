package com.example.felece_ticket.Services;

import com.example.felece_ticket.models.Ticket;
import com.example.felece_ticket.models.Train;
import com.example.felece_ticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets(){return ticketRepository.findAll();}

    public Ticket findbyid(String id){
        return ticketRepository.findbyid(id);
    }


    public String add_ticket(Ticket ticket){
        try{
            ticketRepository.save(ticket);
            return "Bilet Oluşturuldu";
        }
        catch (Exception e){
            return "Bilet oluştulamadı  "+e;
        }
    }

    public List<Ticket> findAllTicketsByUser(String user_id) {
        try {
            Query query = new Query();

            List<Ticket> ticketList ;
            ticketList = ticketRepository.findByUserId(user_id);

            return ticketList;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<Ticket> findAllTicketsByTrain(String train_id) {
        try {
            Query query = new Query();

            List<Ticket> ticketList ;
            ticketList = ticketRepository.findByTrainId(train_id);

            return ticketList;
        }
        catch (Exception e){
            return null;
        }
    }

    public String Update(Ticket ticket,String user_id,String train_id,String seat_number,Boolean is_bought,Boolean is_cancelled,Boolean is_delayed) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ticket.getId()));
            ticket.setUser_id(user_id);
            ticket.setTrain_id(train_id);
            ticket.setSeat_number(seat_number);
            ticket.setIs_bought(is_bought);
            ticket.setIs_cancelled(is_cancelled);
            ticket.setIs_delayed(is_delayed);

            ticketRepository.save(ticket);
            return "Güncelleme Başarılı";
        }
        catch (Exception exception){
            return "Güncelleme Hatalı   "+exception;
        }
    }

    public String cancelled(Ticket ticket){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ticket.getId()));
            ticket.setIs_cancelled(true);
            ticket.setIs_delayed(false);
            ticket.setIs_bought(false);
            ticket.setIs_reserved(false);
            ticketRepository.save(ticket);
            return "Güncelleme Başarılı ";
        }
        catch (Exception e){
            return "Güncelleme Hatalı   "+e;
        }
    }
    public String delayed(Ticket ticket){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ticket.getId()));
            ticket.setIs_cancelled(false);
            ticket.setIs_delayed(true);
            ticket.setIs_bought(false);
            ticket.setIs_reserved(false);
            ticketRepository.save(ticket);
            return "Güncelleme Başarılı ";

        }
        catch (Exception e){
            return "Güncelleme Hatalı   "+e;
        }
    }
    public String sold(Ticket ticket){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ticket.getId()));
            ticket.setIs_cancelled(false);
            ticket.setIs_delayed(false);
            ticket.setIs_bought(true);
            ticket.setIs_reserved(false);
            ticketRepository.save(ticket);
            return "Güncelleme Başarılı ";

        }
        catch (Exception e){
            return "Güncelleme Hatalı   "+e;
        }
    }

    public String reserved(Ticket ticket){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ticket.getId()));
            ticket.setIs_cancelled(false);
            ticket.setIs_delayed(false);
            ticket.setIs_bought(false);
            ticket.setIs_reserved(true);
            ticketRepository.save(ticket);
            return "Güncelleme Başarılı ";

        }
        catch (Exception e){
            return "Güncelleme Hatalı   "+e;
        }
    }

}
