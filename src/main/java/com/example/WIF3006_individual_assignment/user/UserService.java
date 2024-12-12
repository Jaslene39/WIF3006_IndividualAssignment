package com.example.WIF3006_individual_assignment.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long userId);
    void addNewUser(User user);
    void deleteProfile(Long userId);
    void updateProfile(Long userId, String name, String email, Integer age);
}
