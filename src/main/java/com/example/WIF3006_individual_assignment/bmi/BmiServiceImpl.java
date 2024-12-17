package com.example.WIF3006_individual_assignment.bmi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BmiServiceImpl implements BmiService{

    private final BmiRepository bmiRepository;

    public List<Bmi> getAllBmiData(){
        return bmiRepository.findAll();
    }

    public Optional<Bmi> getBmiDataByUserId(Long userId) {
        return bmiRepository.findByUserId(userId);
    }

    public void addBmiData(Bmi bmi) {
        // Validate height and weight
        if (bmi.getHeight() == null || bmi.getHeight() <= 0) {
            throw new IllegalArgumentException("Height must be a valid positive number.");
        }
        if (bmi.getWeight() == null || bmi.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be a valid positive number.");
        }

        // Check if the user already has BMI data
        Optional<Bmi> existingBmi = bmiRepository.findByUserId(bmi.getUser().getId());

        if (existingBmi.isPresent()) {
            throw new IllegalStateException("BMI data for user with id " + bmi.getUser().getId() + " already exists.");
        }
        bmi.calculateBmi();
        bmiRepository.save(bmi);
    }

    public void deleteBmiData(Long bmiId) {
        // Check if the BMI data of specific bmi Id exists
        boolean exists = bmiRepository.existsById(bmiId);
        if(!exists) {
            throw new IllegalStateException(
                    "BMI data with id " + bmiId + " does not exists"
            );
        }
        bmiRepository.deleteById(bmiId);
    }

    public void updateBmi(Long userId, Integer height, Integer weight) {
        // Find the existing BMI data or throw an exception if it doesn't exist
        Bmi bmi = bmiRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "BMI data of the user with user id " + userId + " does not exists"));
        // Validate and update height
        if (height != null) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be a valid positive number.");
            }
            if (!Objects.equals(bmi.getHeight(), height)) {
                bmi.setHeight(height);
            }
        }

        // Validate and update weight
        if (weight != null) {
            if (weight <= 0) {
                throw new IllegalArgumentException("Weight must be a valid positive number.");
            }
            if (!Objects.equals(bmi.getWeight(), weight)) {
                bmi.setWeight(weight);
            }
        }
        bmi.calculateBmi();
        bmiRepository.save(bmi);
    }
}
