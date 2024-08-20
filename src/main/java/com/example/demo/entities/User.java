package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;


//Under entities ve create tables. The user table is created in mysql

//Create new java classes for new tables and add entity

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement id üretici
    Long id;

    String username;
    String password;
}

