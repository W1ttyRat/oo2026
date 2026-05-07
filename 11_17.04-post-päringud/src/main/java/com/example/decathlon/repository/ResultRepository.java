package com.example.decathlon.repository;

import com.example.decathlon.dto.ResultViewDto;
import com.example.decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("SELECT new com.example.decathlon.dto.ResultViewDto(" +
    "r.id, a.name, r.discipline, r.value, r.score) " +
    "FROM Result r JOIN r.athlete a " +
    "WHERE (:discipline IS NULL OR :discipline = '' OR LOWER(r.discipline) = LOWER(:discipline)) " +
    "ORDER BY r.id DESC")
    List<ResultViewDto> findAllForView(@Param("discipline") String discipline);
}
