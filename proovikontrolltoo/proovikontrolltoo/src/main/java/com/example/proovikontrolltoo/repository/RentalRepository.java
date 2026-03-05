package com.example.proovikontrolltoo.repository;

import com.example.proovikontrolltoo.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
