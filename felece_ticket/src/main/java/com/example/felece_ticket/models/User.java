package com.example.felece_ticket.models;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Query;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String tel;
    private Boolean isAdmin;
    @Indexed(unique = true)
    private String username;
    private String password;

    public User(){}

    public User(String name, String surname, String tel, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.isAdmin = false;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTel() {
        return tel;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
