package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import se.experis.com.mefit.mapper.abstracts.GoalMapper;
import se.experis.com.mefit.mapper.abstracts.UserMapper;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.DTOs.GoalDtos.GoalDto;
import se.experis.com.mefit.model.DTOs.UserDtos.PutUserDto;
import se.experis.com.mefit.model.DTOs.UserDtos.UserDto;
import se.experis.com.mefit.service.interfaces.UserService;

@Tag(name = "User", description = "Crud and more relating to users")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final GoalMapper goalMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, GoalMapper goalMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.goalMapper = goalMapper;
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<Set<UserDto>> getAll() {
        Set<UserDto> usersDtos = userService.findAll().stream().map(s -> userMapper.userToUserDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(usersDtos);
    }

    @Operation(summary = "Get a user with given id")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable int id) {
        UserDto userDto = userMapper.userToUserDto(userService.findById(id));
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Add new user")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User newUser = userService.add(userMapper.userDtoToUser(userDto));
        URI location = URI.create("/user" + newUser.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing user with given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, PutUserDto putUserDto) {
        User oldUser = userService.findById(id);
        User updatedUser = userMapper.putUserDtoToUser(id, putUserDto);
        updatedUser.setCurrentGoal(oldUser.getCurrentGoal());
        updatedUser.setGoalHistory(oldUser.getGoalHistory());
        User updatedUserResponse = userService.add(updatedUser);
        URI location = URI.create("users/" + updatedUserResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete existing user with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add goal to user with given id")
    @PatchMapping("{id}")
    public ResponseEntity<Void> addGoal(@PathVariable int id, @RequestBody GoalDto goalDto) {
        User userResponse = userService.addGoal(goalMapper.goalDtoToGoal(goalDto), id);
        URI location = URI.create("goals/" + userResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get goal-history from a user")
    @GetMapping("{id}/history")
    public ResponseEntity<Set<GoalDto>> getGoalHistory(@PathVariable int id) {
        User user = userService.findById(id);
        Set<Goal> userGoals = user.getGoalHistory();
        Set<GoalDto> goalDtos = userGoals.stream().map(s -> goalMapper.goalToGoalDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(goalDtos);
    }

}
