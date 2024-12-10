package com.example.WIF3006_individual_assignment.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // add new user
    @PostMapping(path = "/addNewUser")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    // delete user by userId
    @DeleteMapping(path = "/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    // update user by userId
    @PutMapping(path = "/updateUser/{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) Integer age) {
        userService.updateUser(userId, name, email, age);
    }
}
