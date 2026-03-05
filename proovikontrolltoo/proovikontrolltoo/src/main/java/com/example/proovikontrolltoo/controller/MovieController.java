package com.example.proovikontrolltoo.controller;


import com.example.proovikontrolltoo.dto.MovieSaveDto;
import com.example.proovikontrolltoo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.proovikontrolltoo.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovie() {
        return movieRepository.findAll();
    }

    @GetMapping("movies/available")
    public List<Movie> getMovieAvailable() {
        return movieRepository.findByDays(0);
    }

    @PostMapping("movies")
    public Movie saveMovie(@RequestBody MovieSaveDto movieSaveDto) {
        Movie movie = new Movie();
        movie.setTitle(movieSaveDto.title());
        return movieRepository.save(movie);
    }

    @DeleteMapping("movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }

    @PatchMapping("movies/type/{id}")
    public Movie changeMovie(@PathVariable Long id, @RequestBody MovieSaveDto movieSaveDto) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movie.setType(movieSaveDto.type());
        return movieRepository.save(movie);
    }
}
