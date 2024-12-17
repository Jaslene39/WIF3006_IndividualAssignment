package com.example.WIF3006_individual_assignment.fitnessPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FitnessPlanRepository extends JpaRepository<FitnessPlan, Long> {
    Optional<FitnessPlan> findById(Long id);

    // Custom query to find fitness plan by userId
    List<FitnessPlan> findByUserId(Long userId); // one user can have a list of fitness plans

    @Transactional
    @Modifying
    @Query("DELETE FROM FitnessPlan f WHERE f.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
