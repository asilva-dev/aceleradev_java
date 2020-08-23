package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.From;
import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("FROM Candidate ca WHERE ca.id.company.id= :companyId")
    List<Candidate> findCompanyId(@Param("companyId") Long companyId);

    @Query("FROM Candidate ca WHERE ca.id.acceleration.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("FROM Candidate ca WHERE ca.id.user.id = :userId " +
            "AND ca.id.company.id = :companyId AND ca.id.acceleration.id = :accelerationId")
    Optional<Candidate> findById(@Param("userId") Long userId,
                                 @Param("companyId") Long companyId,
                                 @Param("accelerationId") Long accelerationId);
    Optional<Candidate> findById(CandidateId id);
}