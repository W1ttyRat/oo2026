package com.example.proovikontrolltoo.repository;

import com.example.proovikontrolltoo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByDays(int days);
}
