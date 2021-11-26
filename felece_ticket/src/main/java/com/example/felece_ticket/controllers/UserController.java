package com.example.felece_ticket.controllers;

import com.example.felece_ticket.Services.UserService;
import com.example.felece_ticket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username")String username){
        User user = userService.findbyusername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/passwordControl/{username}/{password}")
    public ResponseEntity<User> passwordControl(@PathVariable("username" )String username,@PathVariable("password") String password){
       User tmp;
        if( userService.passwordControl(username,password)==true){
            tmp= userService.findbyusername(username);
        }
        else{
            tmp=null;
        }
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        String tmp=userService.add(user);
        return new ResponseEntity<>(tmp,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user,String name, String surname,String tel,Boolean isAdmin,String username,String password){
        String tmp=userService.Update(user,name,surname,tel,isAdmin,username,password);
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")String id){
        String tmp=userService.delete(userService.findbyid(id));
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }
}
