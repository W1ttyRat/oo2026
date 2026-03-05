package com.example.proovikontrolltoo.controller;

import com.example.proovikontrolltoo.dto.MovieRentalDto;
import com.example.proovikontrolltoo.entity.Movie;
import com.example.proovikontrolltoo.entity.Rental;
import com.example.proovikontrolltoo.repository.MovieRepository;
import com.example.proovikontrolltoo.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private double premiumPrice = 4;
    private double basicPrice = 3;

    @GetMapping("rentals")
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    // DTO --> mis film (id), mitmeks päevaks võtan
    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<MovieRentalDto> movieRentalDtos) {

        Rental rental = new Rental(); // {id: null, initalfee: null, latefee: null}
        Rental dbRental = rentalRepository.save(rental); // {id: 2, initalfee: null, latefee: null}

        double sum = 0;
        // mis tüüp, muutuja, mida läbi käin
        for (MovieRentalDto movieRentalDto : movieRentalDtos) {
            Movie dbMovie = movieRepository.findById(movieRentalDto.movieId()).orElseThrow();
            dbMovie.setRental(dbRental);
            dbMovie.setDays(movieRentalDto.days());
            switch (dbMovie.getType()) {
                case "NEW" -> sum += premiumPrice * movieRentalDto.days();
                case "REGULAR" -> {
                    if (movieRentalDto.days() <= 3) {
                        sum += basicPrice;
                    } else {
                        sum += basicPrice + basicPrice * (movieRentalDto.days() - 3);
                    }
                }
                case "OLD" -> {
                    if (movieRentalDto.days() <= 5) {
                        sum += basicPrice;
                    } else {
                        sum += basicPrice + basicPrice * (movieRentalDto.days() - 5);
                    }
                }
            }
        }

        dbRental.setInitialFee(sum);
        return rentalRepository.save(dbRental);
    }

    // DTO --> mis film (id), mitu päeva tegelikult rendis oli
    @PostMapping("end-rental")
    public double endRental(@RequestBody List<MovieRentalDto> movieRentalDtos) {

        double sum = 0;
        for (MovieRentalDto movieRentalDto : movieRentalDtos) {
            Movie dbMovie = movieRepository.findById(movieRentalDto.movieId()).orElseThrow();
            Rental rental = dbMovie.getRental();
            // switch case --> MOVIE_SUM arvutamine + summale juurde liitmine
            double movieSum = 0;
            int extraDays = movieRentalDto.days() - dbMovie.getDays(); // rendis olnud päevade arv - tegelik rendis
                                                                       // olnud päevade arv

            if (extraDays > 0) {
                switch (dbMovie.getType()) {
                    case "NEW" -> movieSum += extraDays * premiumPrice;
                    case "REGULAR", "OLD" -> movieSum += extraDays * basicPrice;
                }
            }

            double currentLateFee = rental.getLateFee();

            rental.setLateFee(currentLateFee + movieSum);
            rentalRepository.save(rental);

            dbMovie.setRental(null);
            dbMovie.setDays(0);
            movieRepository.save(dbMovie);
        }
        return sum; // maksmisele lähev summa (võib tulla erinevatest rentalitest)
    }
}
