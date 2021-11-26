package com.example.felece_ticket.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Ticket {
    @Id
    private String id;
    private String user_id;
    private String train_id;
    @Indexed(unique = true)
    private String seat_number;
    private Boolean is_bought;
    private Boolean is_cancelled;
    private Boolean is_delayed;
    private Boolean is_reserved;
    private float price;

    public Ticket(String user_id, String train_id, String seat_number, Boolean is_bought, Boolean is_cancelled, Boolean is_delayed,Boolean is_reserved,float price) {
        this.user_id = user_id;
        this.train_id = train_id;
        this.seat_number = seat_number;
        this.is_bought = is_bought;
        this.is_cancelled = is_cancelled;
        this.is_delayed = is_delayed;
        this.is_reserved=is_reserved;
        this.price=price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(Boolean is_reserved) {
        this.is_reserved = is_reserved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public Boolean getIs_bought() {
        return is_bought;
    }

    public void setIs_bought(Boolean is_bought) {
        this.is_bought = is_bought;
    }

    public Boolean getIs_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(Boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }

    public Boolean getIs_delayed() {
        return is_delayed;
    }

    public void setIs_delayed(Boolean is_delayed) {
        this.is_delayed = is_delayed;
    }
}
