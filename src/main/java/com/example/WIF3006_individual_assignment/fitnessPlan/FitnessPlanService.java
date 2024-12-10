package com.example.WIF3006_individual_assignment.fitnessPlan;

import java.util.List;

public interface FitnessPlanService {
    List<FitnessPlan> getAllFitnessPlans();
    List<FitnessPlan> getFitnessPlansByUserId(Long userId);
    void addNewFitnessPlan(FitnessPlan fitnessPlan);
    void deleteFitnessPlan(Long fitnessPlanId);
    void updateFitnessPlan(Long userId, Long fitnessPlanId, String planTitle, String planDesc, String goal, String duration, String difficultyLevel);

}
