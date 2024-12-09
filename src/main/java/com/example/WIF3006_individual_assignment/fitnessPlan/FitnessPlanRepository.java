package com.example.WIF3006_individual_assignment.fitnessPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FitnessPlanRepository extends JpaRepository<FitnessPlan, Long> {
    Optional<FitnessPlan> findById(Long id);

    // Custom query to find fitness plan by userId
    List<FitnessPlan> findByUserId(Long userId); // one user can have a list of fitness plans
}
