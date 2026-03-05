package com.example.proovikontrolltoo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue

    private Long id;
    private String title;
    private String type;
    private int days;

    @ManyToOne
    private Rental rental;

}
