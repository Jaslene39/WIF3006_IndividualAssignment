package com.example.WIF3006_individual_assignment.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // get all users
    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "/getUserById/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    // add new user
    @PostMapping(path = "/addNewUser")
    public void addNewUser(@RequestBody User user) {
        try {
            userService.addNewUser(user);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Log the error and provide feedback to the caller
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    // delete user by userId
    @DeleteMapping(path = "/deleteUser/{userId}")
    public void deleteProfile(@PathVariable("userId") Long userId) {
        userService.deleteProfile(userId);
    }

    // update user by userId
    @PutMapping(path = "/updateUser/{userId}")
    public void updateProfile(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) Integer age) {
        userService.updateProfile(userId, name, email, age);
    }
}
