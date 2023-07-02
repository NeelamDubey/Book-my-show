package com.example.demo.Services;

import com.example.demo.DTOs.RequestDto.MovieDto;
import com.example.demo.Transformers.MovieTransformer;
import com.example.demo.Models.Movie;
import com.example.demo.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(MovieDto movieDto) {
        Movie movie= MovieTransformer.movieDtoToEntity(movieDto);
        movieRepository.save(movie);
        return "Movie is added succesfully ";
    }
}
