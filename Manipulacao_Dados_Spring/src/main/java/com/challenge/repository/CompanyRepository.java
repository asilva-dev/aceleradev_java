package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT DISTINCT c FROM Company c JOIN c.candidates ca WHERE ca.id.acceleration.id = :accelerationId")
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "SELECT c FROM Company c JOIN c.candidates ca WHERE ca.id.user.id = :userId")
    List<Company> findByUserId(@Param("userId") Long userId);
}