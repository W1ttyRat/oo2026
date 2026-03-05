package com.example.proovikontrolltoo.controller;


import com.example.proovikontrolltoo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.proovikontrolltoo.repository.MovieRepository;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovie() {
        return movieRepository.findAll();
    }

    @PostMapping("movies")
    public List<Movie> addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movieRepository.findAll();
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return movieRepository.findAll();
    }

}
