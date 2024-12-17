package com.example.WIF3006_individual_assignment.bmi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BmiRepository extends JpaRepository<Bmi, Long> {
    Optional<Bmi> findByUserId(Long userId); // one user only have one BMI data

    @Transactional
    @Modifying
    @Query("DELETE FROM Bmi b WHERE b.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
