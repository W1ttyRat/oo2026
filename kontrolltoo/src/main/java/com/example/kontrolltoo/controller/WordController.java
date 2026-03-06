package com.example.kontrolltoo.controller;

import com.example.kontrolltoo.entity.Word;

import com.example.kontrolltoo.exception.WordException;
import com.example.kontrolltoo.repository.WordRepository;
import com.example.kontrolltoo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    //1. punkt

    @GetMapping("words")
    public List<Word> findAll() {
        return wordService.findall();
    }

    @PostMapping("words")
    public Word save(@RequestBody Word word) {
        return wordService.save(word);
    }


    // 2. punkt


    @GetMapping("words/three")
    public long countThreeLetter() {
        return wordService.countThreeLetter();
    }

    @GetMapping("words/divisble")
    public long countDivisibleByThree() {
        return wordService.countDivisibleByThree();
    }

    @GetMapping("words/{id}/prime")
    public boolean isPrimeLength(@PathVariable long id) {
        return wordService.isPrimeLength(id);
    }





}
