package com.example.decathlon.controller;

import com.example.decathlon.dto.AthleteSaveDto;
import com.example.decathlon.dto.AthleteScoreDto;
import com.example.decathlon.dto.ResultSaveDto;
import com.example.decathlon.entity.Athlete;
import com.example.decathlon.entity.Result;
import com.example.decathlon.service.AthleteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping("/athletes")
    public List<Athlete> findAllAthletes() {
        return athleteService.findAllAthletes();
    }

    @GetMapping("/athletes/{id}")
    public Athlete findAthleteById(@PathVariable Long id) {
        return athleteService.findAthleteById(id);
    }

    @PostMapping("/athletes")
    public Athlete saveAthlete(@RequestBody AthleteSaveDto dto) {
        return athleteService.saveAthlete(dto);
    }

    @PatchMapping("/athletes/{id}")
    public Athlete updateAthlete(@PathVariable Long id, @RequestBody AthleteSaveDto dto) {
        return athleteService.updateAthlete(id, dto);
    }

    @DeleteMapping("/athletes/{id}")
    public void deleteAthleteById(@PathVariable Long id) {
        athleteService.deleteAthleteById(id);
    }

    @PostMapping("/results")
    public Result addResult(@RequestBody ResultSaveDto dto) {
        return athleteService.addResult(dto);
    }

    @GetMapping("/athletes/{id}/total-score")
    public Integer getTotalScore(@PathVariable Long id) {
        return athleteService.getTotalScore(id);
    }

    @GetMapping("/athletes/scores")
    public List<AthleteScoreDto> findAllAthletesWithScores() {
        return athleteService.findAllAthletesWithScores();
    }
}
