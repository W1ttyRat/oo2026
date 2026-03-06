package com.example.kontrolltoo.repository;

import com.example.kontrolltoo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface WordRepository extends JpaRepository<Word, Long> {
}
