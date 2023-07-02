package com.example.demo.Repositories;

import com.example.demo.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByLocation(String location);
}
