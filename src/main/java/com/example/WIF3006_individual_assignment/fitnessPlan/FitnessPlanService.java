package com.example.WIF3006_individual_assignment.fitnessPlan;

import com.example.WIF3006_individual_assignment.user.User;
import com.example.WIF3006_individual_assignment.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FitnessPlanService {
    private final FitnessPlanRepository fitnessPlanRepository;
    private final UserRepository userRepository;

    @Autowired
    public FitnessPlanService(FitnessPlanRepository fitnessPlanRepository, UserRepository userRepository) {
        this.fitnessPlanRepository = fitnessPlanRepository;
        this.userRepository = userRepository;
    }

    public List<FitnessPlan> getAllFitnessPlans(){
        return fitnessPlanRepository.findAll();
    }

    public List<FitnessPlan> getFitnessPlansByUserId(Long userId){
        return fitnessPlanRepository.findByUserId(userId);
    }

    public void addNewFitnessPlan(FitnessPlan fitnessPlan) {
        // Check if the user exists
        boolean userExists  = userRepository.existsById(fitnessPlan.getUser().getId());
        if(!userExists ) {
            throw new IllegalStateException(
                    "User with id " + fitnessPlan.getUser().getId() + " does not exists"
            );
        }
        fitnessPlanRepository.save(fitnessPlan);
    }

    public void deleteFitnessPlan(Long fitnessPlanId) {
        // Check if the fitness plan of specific fitness plan Id exists
        boolean exists = fitnessPlanRepository.existsById(fitnessPlanId);
        if(!exists) {
            throw new IllegalStateException(
                    "Fitness plan with id " + fitnessPlanId + " does not exists"
            );
        }
        fitnessPlanRepository.deleteById(fitnessPlanId);
    }

    @Transactional
    public void updateFitnessPlan(Long userId, Long fitnessPlanId, String planTitle, String planDesc, String goal, String duration, String difficultyLevel) {
        // Check if the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + userId + " does not exist"));

        // Check if the fitness plan exists
        FitnessPlan fitnessPlan = fitnessPlanRepository.findById(fitnessPlanId)
                .orElseThrow(() -> new IllegalStateException(
                        "Fitness plan with id " + fitnessPlanId + " does not exist"));

        // Verify that the fitness plan belongs to the user
        if (!Objects.equals(fitnessPlan.getUser().getId(), userId)) {
            throw new IllegalStateException("Fitness plan does not belong to the user with id " + userId);
        }

        // Update fields if non-null and valid
        if (planTitle != null && !planTitle.trim().isEmpty() && !Objects.equals(fitnessPlan.getPlanTitle(), planTitle)) {
            fitnessPlan.setPlanTitle(planTitle);
        }
        if (planDesc != null && !planDesc.trim().isEmpty() && !Objects.equals(fitnessPlan.getPlanDesc(), planDesc)) {
            fitnessPlan.setPlanDesc(planDesc);
        }
        if (goal != null && !goal.trim().isEmpty() && !Objects.equals(fitnessPlan.getGoal(), goal)) {
            fitnessPlan.setGoal(goal);
        }
        if (duration != null && !duration.trim().isEmpty() && !Objects.equals(fitnessPlan.getDuration(), duration)) {
            fitnessPlan.setDuration(duration);
        }
        if (difficultyLevel != null && !difficultyLevel.trim().isEmpty() && !Objects.equals(fitnessPlan.getDifficultyLevel(), difficultyLevel)) {
            fitnessPlan.setDifficultyLevel(difficultyLevel);
        }

        // Save the updated fitness plan
        fitnessPlanRepository.save(fitnessPlan);
    }
}
