package com.example.kontrolltoo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ThreeLetterLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // mitu 3-tähelist sõna oli päringu hetkel
    private Long resultCount;

    private LocalDateTime queriedAt;

    public ThreeLetterLog(Long resultCount, LocalDateTime queriedAt) {
        this.resultCount = resultCount;
        this.queriedAt = queriedAt;
    }
}
