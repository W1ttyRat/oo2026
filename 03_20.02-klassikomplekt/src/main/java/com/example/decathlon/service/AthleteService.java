package com.example.decathlon.service;

import com.example.decathlon.dto.AthleteSaveDto;
import com.example.decathlon.dto.AthleteScoreDto;
import com.example.decathlon.dto.ResultSaveDto;
import com.example.decathlon.entity.Athlete;
import com.example.decathlon.entity.Result;
import com.example.decathlon.exception.AthleteNotFoundException;
import com.example.decathlon.repository.AthleteRepository;
import com.example.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ResultRepository resultRepository;

    public List<Athlete> findAllAthletes() {
        return athleteRepository.findAll();
    }

    public Athlete findAthleteById(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new AthleteNotFoundException(id));
    }

    public Athlete saveAthlete(AthleteSaveDto athleteSaveDto) {
        Athlete athlete = new Athlete();
        athlete.setName(athleteSaveDto.name());
        return athleteRepository.save(athlete);
    }

    public Athlete updateAthlete(Long id, AthleteSaveDto athleteSaveDto) {
        Athlete athlete = findAthleteById(id);
        athlete.setName(athleteSaveDto.name());
        return athleteRepository.save(athlete);
    }

    public void deleteAthleteById(Long id) {
        if (!athleteRepository.existsById(id)) {
            throw new AthleteNotFoundException(id);
        }
        athleteRepository.deleteById(id);
    }

    public Result addResult(ResultSaveDto dto) {
        Athlete athlete = findAthleteById(dto.athleteId());

        int calculatedScore = DecathlonCalculator.calculate(dto.discipline(), dto.value());

        Result result = new Result();
        result.setDiscipline(dto.discipline());
        result.setValue(dto.value());
        result.setScore(calculatedScore);
        result.setAthlete(athlete);

        return resultRepository.save(result);
    }

    public Integer getTotalScore(Long athleteId) {
        Athlete athlete = findAthleteById(athleteId);
        return athlete.getResults().stream()
                .mapToInt(Result::getScore)
                .sum();
    }

    public List<AthleteScoreDto> findAllAthletesWithScores() {
        List<Athlete> athletes = athleteRepository.findAll();

        return athletes.stream().map(athlete -> {
            int totalScore = athlete.getResults().stream().mapToInt(Result::getScore).sum();
            return new AthleteScoreDto(athlete.getId(), athlete.getName(), totalScore);
        }).toList();
    }
}
