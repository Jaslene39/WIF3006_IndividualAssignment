package com.example.WIF3006_individual_assignment.bmi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BmiRepository extends JpaRepository<Bmi, Long> {
    Optional<Bmi> findByUserId(Long userId); // one user only have one BMI data
}
