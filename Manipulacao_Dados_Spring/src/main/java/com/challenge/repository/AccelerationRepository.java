package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    @Query("FROM Acceleration a JOIN a.candidates ca WHERE ca.id.company.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

    Optional<Acceleration> findById(Long id);

    Optional<Acceleration> findByName(String name);
}