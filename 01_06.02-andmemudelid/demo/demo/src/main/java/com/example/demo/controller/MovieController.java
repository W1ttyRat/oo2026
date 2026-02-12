package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll(); // leiab kõik filmid
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> deleteMovies(@PathVariable Long id) {
        movieRepository.deleteById(id); // kustutab filmi, id järgi
        return movieRepository.findAll();
    }

    @PostMapping("movies")
    public List<Movie> saveMovies(@RequestBody Movie movie) {
        movieRepository.save(movie); // salvestab
        return movieRepository.findAll(); // uuenenud list
    }
}
