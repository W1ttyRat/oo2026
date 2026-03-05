package com.example.proovikontrolltoo.repository;

import com.example.proovikontrolltoo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
