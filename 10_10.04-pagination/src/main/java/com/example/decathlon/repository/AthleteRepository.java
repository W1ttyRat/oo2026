package com.example.decathlon.repository;

import com.example.decathlon.dto.AthleteScoreDto;
import com.example.decathlon.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query(value = "SELECT new com.example.decathlon.dto.AthleteScoreDto(a.id, a.name, a.country, CAST(COALESCE(SUM(r.score), 0) AS Long)) " +
                   "FROM Athlete a LEFT JOIN a.results r " +
                   "WHERE (:country IS NULL OR :country = '' OR LOWER(a.country) LIKE LOWER(CONCAT('%', :country, '%'))) " +
                   "GROUP BY a.id, a.name, a.country",
           countQuery = "SELECT COUNT(a) FROM Athlete a " +
                        "WHERE (:country IS NULL OR :country = '' OR LOWER(a.country) LIKE LOWER(CONCAT('%', :country, '%')))")
    Page<AthleteScoreDto> findAllWithScores(Pageable pageable, @Param("country") String country);
}
