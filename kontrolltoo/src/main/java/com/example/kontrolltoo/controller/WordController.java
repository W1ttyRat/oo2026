package com.example.kontrolltoo.controller;

import com.example.kontrolltoo.dto.MovieSaveDto;
import com.example.kontrolltoo.entity.Movie;
import com.example.kontrolltoo.repository.MovieRepository;
import com.example.kontrolltoo.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping("/movies")
    public Movie save(@RequestBody MovieSaveDto dto) {
        return movieService.save(dto);
    }

    @PatchMapping("/movies/{id}")
    public Movie update(@PathVariable Long id, @RequestBody MovieSaveDto dto) {
        return movieService.update(id, dto);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteById(@PathVariable Long id) {
        movieService.deleteById(id);
    }
}
