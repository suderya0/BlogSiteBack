package com.example.demo.repos;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


//JpaRepository Database e query atmaya yarayan bir sınıf hazır methodlar bulunduruyor içinde

public interface UserRepository extends JpaRepository<User, Long> {

}
