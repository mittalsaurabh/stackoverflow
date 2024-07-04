package org.example.Controller;

import org.example.App.Facade;
import org.example.Entity.User;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Facade facade;

    public UserController(UserService userService, Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return facade.addUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getUsers() {
        return facade.getUsers();
    }
}