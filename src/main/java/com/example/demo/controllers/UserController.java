package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Sayfa geçişleri için burası kulanılır

@RestController
@RequestMapping("/users")

public class UserController {

    private UserServices userServices; //Spring burada constructor inection ile objeyi kendi inject ediyor

    public UserController(UserServices userServices) {
        this.userServices = userServices;

    }

    @GetMapping
    public List<User> getAllUsers(){
        return userServices.getAllUsers(); //query gonderiyor
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) { //RequestBody istenilen içerikler
        return userServices.saveOneUser(newUser); //query gonderiyor
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){ //userid yi Path variable ile alınıyor
        return userServices.getOneUser(userId);  //orElse userId database de mevcut değilse
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
         return userServices.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userServices.deleteOneUser(userId);
    }

}
