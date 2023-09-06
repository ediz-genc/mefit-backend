package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.service.UserService;

@CrossOrigin(origins = "*")
@Tag(name = "User", description = "Crud and more relating to users")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<Set<User>> getAll() {
        Set<User> users = userService.findAll();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Set<User>>(users, responseHeaders, HttpStatus.OK);
    }

    @Operation(summary = "Get a user with given id")
    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Add new user")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.add(user);
        URI location = URI.create("/user" + newUser.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing user with given id")
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        userService.add(updatedUser);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete existing user with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add goal to user with given id")
    @PatchMapping("{id}")
    public ResponseEntity<User> addGoal(@PathVariable int id, @RequestBody Goal goal) {
        User userResponse = userService.addGoal(goal, id);
        URI location = URI.create("goals/" + userResponse.getId());
        return ResponseEntity.created(location).build();
    }

}
