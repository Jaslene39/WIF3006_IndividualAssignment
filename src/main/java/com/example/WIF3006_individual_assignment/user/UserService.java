package com.example.WIF3006_individual_assignment.user;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addNewUser(User user);
    void deleteUser(Long userId);
    void updateUser(Long userId, String name, String email, Integer age);
}
