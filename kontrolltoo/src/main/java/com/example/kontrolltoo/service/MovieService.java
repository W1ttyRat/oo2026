package com.example.kontrolltoo.service;

import com.example.kontrolltoo.dto.MovieSaveDto;
import com.example.kontrolltoo.entity.Movie;
import com.example.kontrolltoo.exception.MovieNotFoundException;
import com.example.kontrolltoo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie save(MovieSaveDto movieSaveDto) {
        Movie movie = new Movie();
        movie.setTitle(movieSaveDto.title());
        movie.setDescription(movieSaveDto.description());
        return movieRepository.save(movie);
    }

    public Movie update(Long id, MovieSaveDto movieSaveDto) {
        Movie movie = findById(id);
        movie.setTitle(movieSaveDto.title());
        movie.setDescription(movieSaveDto.description());
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }
}
