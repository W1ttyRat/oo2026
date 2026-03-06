package com.example.kontrolltoo.repository;

import com.example.kontrolltoo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //List <Movie> findByDays(int days);
}
