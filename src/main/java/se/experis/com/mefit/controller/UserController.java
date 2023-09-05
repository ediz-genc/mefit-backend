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
import se.experis.com.mefit.mapper.GoalMapper;
import se.experis.com.mefit.mapper.UserMapper;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.DTOs.GoalDto;
import se.experis.com.mefit.model.DTOs.UserDto;
import se.experis.com.mefit.service.UserService;

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
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User updatedUser = userService.update(id, userMapper.userDtoToUser(userDto));
        updatedUser.setId(userDto.getId());// Don't know if this works
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
    public ResponseEntity<Void> addGoal(@PathVariable int id, @RequestBody GoalDto goalDto) {
        User userResponse = userService.addGoal(goalMapper.goalDtoToGoal(goalDto), id);
        URI location = URI.create("goals/" + userResponse.getId());
        return ResponseEntity.created(location).build();
    }

}
