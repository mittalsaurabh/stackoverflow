package org.example.Controller;

import org.example.App.AppService;
import org.example.Entity.User;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AppService appService;

    public UserController(UserService userService, AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return appService.addUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getUsers() {
        return appService.getUsers();
    }
}