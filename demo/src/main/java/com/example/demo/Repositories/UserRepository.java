package com.example.demo.Repositories;

import com.example.demo.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Ticket,Integer> {
}
