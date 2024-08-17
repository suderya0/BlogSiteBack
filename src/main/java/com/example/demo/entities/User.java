package com.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


//Under entities ve create tables. The user table is created in mysql

//Create new java classes for new tables and add entity

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    Long id;

    String username;
    String password;
}

