package com.example.felece_ticket.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Train {
    @Id
    private String id;
    private String departure_id;
    private String arrival_id;
    private String empty_seats;
    private String reserved_seats;
    private String sold_seats;
    private Date date;
    private int total_ticket;
    private float price;

    public Train(String departure_id, String arrival_id, String empty_seats, String reserved_seats, String sold_seats, Date date, int total_ticket,float price) {
        this.departure_id = departure_id;
        this.arrival_id = arrival_id;
        this.reserved_seats = reserved_seats;
        this.sold_seats = sold_seats;
        this.date = date;
        this.total_ticket = total_ticket;
        this.price=price;
        this.empty_seats = empty_seats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal_ticket() {
        return total_ticket;
    }

    public void setTotal_ticket(int total_ticket) {
        this.total_ticket = total_ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(String departure_id) {
        this.departure_id = departure_id;
    }

    public String getArrival_id() {
        return arrival_id;
    }

    public void setArrival_id(String arrival_id) {
        this.arrival_id = arrival_id;
    }

    public String getEmpty_seats() {
        return empty_seats;
    }

    public void setEmpty_seats(String empty_seats) {
        this.empty_seats = empty_seats;
    }

    public String getReserved_seats() {
        return reserved_seats;
    }

    public void setReserved_seats(String reserved_seats) {
        this.reserved_seats = reserved_seats;
    }

    public String getSold_seats() {
        return sold_seats;
    }

    public void setSold_seats(String sold_seats) {
        this.sold_seats = sold_seats;
    }
}
