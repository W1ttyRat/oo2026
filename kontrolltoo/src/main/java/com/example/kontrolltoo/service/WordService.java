package com.example.kontrolltoo.service;

import com.example.kontrolltoo.entity.Word;
import com.example.kontrolltoo.exception.WordException;
import com.example.kontrolltoo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Word> findall() {
        return wordRepository.findAll();
    }

    public Word save(Word word) {
        WordException.validateWord(word.getWord());
        return wordRepository.save(word);
    }

    public long countThreeLetter() {
        List<Word> words = wordRepository.findAll();
        long count = 0;
        for (Word word : words) {
            if (word.getWord() != null && word.getWord().length() == 3) {
                count++;
            }
        }
        return count;
    }

    public long countDivisibleByThree() {
        List<Word> words = wordRepository.findAll();
        long count = 0;

        for (Word word : words) {
            if (word.getWord() != null && word.getWord().length() % 3 == 0) {
                count++;
            }
        }
        return count;
    }

    public boolean isPrimeLength(Long id) {
        Word word = wordRepository.findById(id).orElseThrow(() -> new WordException("sõna mille id on " + id + ", ei eksisteeri"));

        if (word.getWord() == null) {
            throw new WordException("Sõnal puudub väärtus");
        }

        int len = word.getWord().length();
        if (len < 2) return false;
        for (int i = 2; i * i <= len; i++) {
            if (len % i == 0) return false;
        }
        return true;
    }

}
