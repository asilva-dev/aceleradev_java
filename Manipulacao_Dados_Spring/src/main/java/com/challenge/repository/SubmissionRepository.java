package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query(value = "FROM Submission submission JOIN submission.id.challenge challenge " +
            "JOIN challenge.accelerations acceleration " +
            "WHERE challenge.id = :challengeId " +
            "AND acceleration.id = :accelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId,
                                                        @Param("accelerationId") Long accelerationId);

    @Query("SELECT COALESCE(max(s.score), 0) FROM Submission s WHERE s.id.challenge.id = :challengeId")
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

}