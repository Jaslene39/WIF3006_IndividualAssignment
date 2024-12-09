package com.example.WIF3006_individual_assignment.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void addNewUser(User user) {
        Optional<User> userOptional =  userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("The email is taken");
        }
        userRepository.save(user);
    };

    public void deleteProfile(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException(
                   "user with id " + userId + " does not exists"
            );
        }
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
        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
        if(age != null && age > 0 && !Objects.equals(user.getAge(), age)) {
            user.setAge(age);
        }
    }
}
