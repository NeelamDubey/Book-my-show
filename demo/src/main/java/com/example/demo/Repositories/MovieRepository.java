package com.example.demo.Repositories;

import com.example.demo.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
