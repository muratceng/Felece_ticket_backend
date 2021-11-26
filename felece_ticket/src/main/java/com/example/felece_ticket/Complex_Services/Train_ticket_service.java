package com.example.felece_ticket.Complex_Services;

import com.example.felece_ticket.Services.CitiesService;
import com.example.felece_ticket.Services.TicketService;
import com.example.felece_ticket.Services.TrainService;
import com.example.felece_ticket.Services.UserService;
import com.example.felece_ticket.models.Ticket;
import com.example.felece_ticket.models.Train;
import com.example.felece_ticket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Train_ticket_service {
    private final TicketService ticketService;
    private final TrainService trainService;
    private final UserService userService;
    private final CitiesService citiesService;

    @Autowired
    public Train_ticket_service(TicketService ticketService, TrainService trainService, UserService userService, CitiesService citiesService) {
        this.ticketService = ticketService;
        this.trainService = trainService;
        this.userService = userService;
        this.citiesService = citiesService;
    }

    public String buy_ticket(User user, Train train,String seat_number){
        String tmp= ticketService.add_ticket(new Ticket(user.getId(), train.getId(), seat_number, true, false, false,false,train.getPrice()));
        if(tmp.equals("Bilet Oluşturuldu")==true)
        {
            trainService.updateEmptySeats(train, seat_number, true);
            trainService.updateSoldSeats(train, seat_number, false);
            return String.valueOf(tmp.equals("Bilet Oluşturuldu"));
        }
        else
        {
            return "biletiniz alınamadı! ";
        }
    }

    public String reserved_ticket(User user, Train train, String seat_number){
        String tmp = ticketService.add_ticket(new Ticket(user.getId(), train.getId(), seat_number, false, false, false, true, train.getPrice()));
        if(tmp.equals("Bilet Oluşturuldu")==true)
        {
            trainService.updateEmptySeats(train,seat_number,true);
            trainService.updateReservedSeats(train,seat_number,false);
            return "Biletiniz başarıyla rezerve edilmiştir! ";
        }
        else
        {
            return "Biletiniz rezerve edilemedi! ";
        }

    }

    public String sold_cancelled_ticket(String ticket_id){
        Ticket ticket = ticketService.findbyid(ticket_id);
        String tmp = ticketService.cancelled(ticket);
        String train_id=ticket.getTrain_id();
        if(tmp.equals("Güncelleme Başarılı ")){
            trainService.updateEmptySeats(trainService.findbyid(train_id),ticket.getSeat_number(),false);
            trainService.updateSoldSeats(trainService.findbyid(train_id),ticket.getSeat_number(),true);
            return "Biletiniz iptal edildi";
        }
        else {
            return "Biletiniz iptal edilemedi! ";
        }
    }

    public String reserved_cancelled_ticket(String ticket_id){
        Ticket ticket = ticketService.findbyid(ticket_id);
        String tmp = ticketService.cancelled(ticket);
        Train train1= trainService.findbyid(ticket.getTrain_id());
        if(tmp.equals("Güncelleme Başarılı ")){
            trainService.updateEmptySeats(train1,ticket.getSeat_number(),false);
            trainService.updateReservedSeats(train1,ticket.getSeat_number(),true);
            return "Biletiniz iptal edildi";
        }
        else {
            return "Biletiniz iptal edilemedi! ";
        }
    }


    public String buy_delayed_ticket(String ticket_id){
        Ticket ticket = ticketService.findbyid(ticket_id);
        String tmp = ticketService.delayed(ticket);
        String train_id=ticket.getTrain_id();
        if(tmp.equals("Güncelleme Başarılı ")){
            trainService.updateEmptySeats(trainService.findbyid(train_id),ticket.getSeat_number(),false);
            trainService.updateSoldSeats(trainService.findbyid(train_id),ticket.getSeat_number(),true);
            return "Biletiniz iptal edildi";
        }
        else {
            return "Biletiniz iptal edilemedi! ";
        }
    }
    public String reserved_delayed_ticket(String ticket_id){
        Ticket ticket = ticketService.findbyid(ticket_id);
        String tmp = ticketService.cancelled(ticket);
        String train_id=ticket.getTrain_id();
        if(tmp.equals("Güncelleme Başarılı ")){
            trainService.updateEmptySeats(trainService.findbyid(train_id),ticket.getSeat_number(),false);
            trainService.updateReservedSeats(trainService.findbyid(train_id),ticket.getSeat_number(),true);
            return "Biletiniz iptal edildi";
        }
        else {
            return "Biletiniz iptal edilemedi! ";
        }
    }

}
