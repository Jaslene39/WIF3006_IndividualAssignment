package com.example.WIF3006_individual_assignment.fitnessPlan;

import com.example.WIF3006_individual_assignment.user.User;
import com.example.WIF3006_individual_assignment.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FitnessPlanServiceImpl implements FitnessPlanService{

    private final FitnessPlanRepository fitnessPlanRepository;
    private final UserRepository userRepository;

    public List<FitnessPlan> getAllFitnessPlans(){
        return fitnessPlanRepository.findAll();
    }

    public List<FitnessPlan> getFitnessPlansByUserId(Long userId){
        return fitnessPlanRepository.findByUserId(userId);
    }

    public void addNewFitnessPlan(FitnessPlan fitnessPlan) {
        // Check if the user exists
        boolean userExists = userRepository.existsById(fitnessPlan.getUser().getId());
        if (!userExists) {
            throw new IllegalStateException(
                    "User with id " + fitnessPlan.getUser().getId() + " does not exist"
            );
        }

        // Validate that 'plantitle' is not null or empty
        if (fitnessPlan.getPlanTitle() == null || fitnessPlan.getPlanTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Plantitle cannot be empty");
        }

        // Validate that 'plandesc' is not null or empty
        if (fitnessPlan.getPlanDesc() == null || fitnessPlan.getPlanDesc().trim().isEmpty()) {
            throw new IllegalArgumentException("Plan description cannot be empty");
        }

        // Validate that 'goal' is not null or empty
        if (fitnessPlan.getGoal() == null || fitnessPlan.getGoal().trim().isEmpty()) {
            throw new IllegalArgumentException("Goal cannot be empty");
        }

        // Validate that 'duration' is not null, empty, and follows the correct format
        if (fitnessPlan.getDuration() == null || fitnessPlan.getDuration().trim().isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be empty");
        }

        // Validate that 'difficultylevel' is not null or empty
        if (fitnessPlan.getDifficultyLevel() == null || fitnessPlan.getDifficultyLevel().trim().isEmpty()) {
            throw new IllegalArgumentException("Difficulty level cannot be empty");
        }

        // Validate that 'difficultylevel' is a valid level (e.g., easy, medium, hard)
        String[] validDifficultyLevels = {"Beginner", "Intermediate", "Advanced"};
        boolean validLevel = Arrays.stream(validDifficultyLevels)
                .anyMatch(level -> level.equalsIgnoreCase(fitnessPlan.getDifficultyLevel().trim()));
        if (!validLevel) {
            throw new IllegalArgumentException("Difficulty level must be one of the following: Beginner, Intermediate, or Advanced");
        }

        // Save the fitness plan if all validations pass
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

        // Validate and update the planTitle
        if (planTitle != null && !planTitle.trim().isEmpty() && !Objects.equals(fitnessPlan.getPlanTitle(), planTitle)) {
            fitnessPlan.setPlanTitle(planTitle);
        } else if (planTitle != null && planTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Plan title cannot be empty");
        }

        // Validate and update the planDesc
        if (planDesc != null && !planDesc.trim().isEmpty() && !Objects.equals(fitnessPlan.getPlanDesc(), planDesc)) {
            fitnessPlan.setPlanDesc(planDesc);
        } else if (planDesc != null && planDesc.trim().isEmpty()) {
            throw new IllegalArgumentException("Plan description cannot be empty");
        }

        // Validate and update the goal
        if (goal != null && !goal.trim().isEmpty() && !Objects.equals(fitnessPlan.getGoal(), goal)) {
            fitnessPlan.setGoal(goal);
        } else if (goal != null && goal.trim().isEmpty()) {
            throw new IllegalArgumentException("Goal cannot be empty");
        }

        // Validate and update the duration
        if (duration != null && duration.trim().isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be empty");
        }

        // Validate and update the difficultyLevel
        if (difficultyLevel != null && !difficultyLevel.trim().isEmpty() && !Objects.equals(fitnessPlan.getDifficultyLevel(), difficultyLevel)) {
            // Validate the difficulty level
            String[] validDifficultyLevels = {"Beginner", "Intermediate", "Advanced"};
            boolean validLevel = Arrays.stream(validDifficultyLevels)
                    .anyMatch(level -> level.equalsIgnoreCase(difficultyLevel.trim()));
            if (!validLevel) {
                throw new IllegalArgumentException("Difficulty level must be one of the following: Beginner, Intermediate, or Advanced");
            }
            fitnessPlan.setDifficultyLevel(difficultyLevel);
        } else if (difficultyLevel != null && difficultyLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Difficulty level cannot be empty");
        }

        // Save the updated fitness plan
        fitnessPlanRepository.save(fitnessPlan);
    }
}
