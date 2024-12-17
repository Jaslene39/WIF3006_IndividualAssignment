package com.example.WIF3006_individual_assignment.user;

import com.example.WIF3006_individual_assignment.bmi.BmiRepository;
import com.example.WIF3006_individual_assignment.fitnessPlan.FitnessPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FitnessPlanRepository fitnessPlanRepository;
    private final BmiRepository bmiRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void addNewUser(User user) {
        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Validate age is an integer and check for invalid values (e.g., non-numeric or negative)
        if (user.getAge() == null || user.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be a valid positive integer greater than 0");
        }

        Optional<User> userOptional =  userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("The email is taken");
        }
        userRepository.save(user);
    };

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public void deleteProfile(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException(
                   "user with id " + userId + " does not exists"
            );
        }
        // Delete related fitness plans
        fitnessPlanRepository.deleteByUserId(userId);

        // Delete related BMI records
        bmiRepository.deleteByUserId(userId);

        // Delete the user
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateProfile(Long userId, String name, String email, Integer age) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exists" ));

        if(name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        // Validate and update the email
        if (email != null && email.trim().length() > 0 && !Objects.equals(user.getEmail(), email)) {
            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format");
            }
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            user.setEmail(email.trim());
        }
        // Validate and update the age
        if (age != null && !Objects.equals(user.getAge(), age)) {
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be a valid positive integer greater than 0");
            }
            user.setAge(age);
        }
    }
}
