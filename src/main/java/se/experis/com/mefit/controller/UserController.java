package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.mapper.abstracts.GoalMapper;
import se.experis.com.mefit.mapper.abstracts.ProgramMapper;
import se.experis.com.mefit.mapper.abstracts.UserMapper;
import se.experis.com.mefit.mapper.abstracts.WorkoutMapper;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.GoalDtos.GoalDto;
import se.experis.com.mefit.model.DTOs.ProgramDtos.ProgramDto;
import se.experis.com.mefit.model.DTOs.UserDtos.PutUserDto;
import se.experis.com.mefit.model.DTOs.UserDtos.UserDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.WorkoutDto;
import se.experis.com.mefit.service.interfaces.GoalService;
import se.experis.com.mefit.service.interfaces.UserService;

@CrossOrigin(origins = "*")
@Tag(name = "User", description = "Crud and more relating to users")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final GoalMapper goalMapper;
    private final WorkoutMapper workoutMapper;
    private final ProgramMapper programMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, GoalMapper goalMapper,
            GoalService goalService, WorkoutMapper workoutMapper, ProgramMapper programMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.goalMapper = goalMapper;
        this.workoutMapper = workoutMapper;
        this.programMapper = programMapper;

    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<Set<UserDto>> getAll() {
        Set<UserDto> usersDtos = userService.findAll().stream().map(s -> userMapper.userToUserDto(s))
                .collect(Collectors.toSet());
        return new ResponseEntity<Set<UserDto>>(usersDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get a user with given id")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id) {
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
    public ResponseEntity<Void> updateUser(@PathVariable String id, PutUserDto putUserDto) {
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
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add goal to user with given id")
    @PatchMapping("{id}")
    public ResponseEntity<Void> addGoal(@PathVariable String id, @RequestBody GoalDto goalDto) {
        User userResponse = userService.addGoal(goalMapper.goalDtoToGoal(goalDto), id);
        URI location = URI.create("goals/" + userResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get goal-history from a user")
    @GetMapping("{id}/history")
    public ResponseEntity<Set<GoalDto>> getGoalHistory(@PathVariable String id) {
        User user = userService.findById(id);
        Set<Goal> userGoals = user.getGoalHistory();
        Set<GoalDto> goalDtos = userGoals.stream().map(s -> goalMapper.goalToGoalDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(goalDtos);
    }

    @Operation(summary = "Check if a user exists")
    @GetMapping("{id}/exists")
    public ResponseEntity<Boolean> userExists(@PathVariable String id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @Operation(summary = "Get completed workouts in users current goal")
    @GetMapping("{id}/workouts/completed")
    ResponseEntity<Set<WorkoutDto>> getCompletedWorkouts(@PathVariable String id) {
        User user = userService.findById(id);
        Goal currentGoal = user.getCurrentGoal();
        Set<Workout> completedWorkouts = currentGoal.getCompletedWorkouts();
        Set<WorkoutDto> completedWorkoutDtos = completedWorkouts.stream().map(s -> workoutMapper.workoutToWorkoutDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(completedWorkoutDtos);
    }

    @Operation(summary = "Get pending workouts in users current goal")
    @GetMapping("{id}/workouts/pending")
    ResponseEntity<Set<WorkoutDto>> getPendingWorkouts(@PathVariable String id) {
        User user = userService.findById(id);
        Goal currentGoal = user.getCurrentGoal();
        Set<Workout> pendingWorkouts = currentGoal.getWorkouts();
        Set<WorkoutDto> pendingWorkoutDtos = pendingWorkouts.stream().map(s -> workoutMapper.workoutToWorkoutDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(pendingWorkoutDtos);
    }

    @Operation(summary = "Get completed programs in users current goal")
    @GetMapping("{id}/programs/completed")
    ResponseEntity<Set<ProgramDto>> getCompletedPrograms(@PathVariable String id) {
        User user = userService.findById(id);
        Goal currentGoal = user.getCurrentGoal();
        Set<Program> completedPrograms = currentGoal.getCompletedPrograms();
        Set<ProgramDto> completedProgramDtos = completedPrograms.stream().map(s -> programMapper.programToProgramDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(completedProgramDtos);
    }

    @Operation(summary = "Get pending programs in users current goal")
    @GetMapping("{id}/programs/pending")
    ResponseEntity<Set<ProgramDto>> getPendingPrograms(@PathVariable String id) {
        User user = userService.findById(id);
        Goal currentGoal = user.getCurrentGoal();
        Set<Program> pendingPrograms = currentGoal.getPrograms();
        Set<ProgramDto> pendingWorkoutDtos = pendingPrograms.stream().map(s -> programMapper.programToProgramDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(pendingWorkoutDtos);
    }

    @Operation(summary = "Get the current goal of a user with given id")
    @GetMapping("{id}/goal")
    ResponseEntity<GoalDto> getCurrentGoal(@PathVariable String id) {
        User user = userService.findById(id);
        Goal currentGoal = user.getCurrentGoal();
        GoalDto goalDto = goalMapper.goalToGoalDto(currentGoal);
        return ResponseEntity.ok(goalDto);
    }

}
