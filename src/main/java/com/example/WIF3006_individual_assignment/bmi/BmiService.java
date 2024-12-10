package com.example.WIF3006_individual_assignment.bmi;

import java.util.List;
import java.util.Optional;

public interface BmiService {
    List<Bmi> getAllBmiData();
    Optional<Bmi> getBmiDataByUserId(Long userId);
    void addBmiData(Bmi bmi);
    void deleteBmiData(Long bmiId);
    void updateBmi(Long userId, Integer height, Integer weight);
}
