package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import se.experis.com.mefit.mapper.abstracts.WorkoutMapper;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.GetWorkoutExerciseDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.PutWorkoutDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.WorkoutDto;
import se.experis.com.mefit.service.interfaces.WorkoutService;

@CrossOrigin
@Tag(name = "Workout", description = "Crud and more mor workout")
@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    @Autowired
    public WorkoutController(WorkoutService workoutService, WorkoutMapper workoutMapper) {
        this.workoutService = workoutService;
        this.workoutMapper = workoutMapper;
    }

    @Operation(summary = "Get all workouts")
    @GetMapping
    public ResponseEntity<Set<WorkoutDto>> getAll() {
        Set<WorkoutDto> workoutDtos = workoutService.findAll().stream().map(s -> workoutMapper.workoutToWorkoutDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(workoutDtos);
    }

    @Operation(summary = "Get a workout with a given id")
    @GetMapping("{id}")
    public ResponseEntity<WorkoutDto> getById(@PathVariable int id) {
        WorkoutDto workoutDto = workoutMapper.workoutToWorkoutDto(workoutService.findById(id));
        return ResponseEntity.ok(workoutDto);
    }

    @Operation(summary = "Add a new workout")
    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody WorkoutDto workoutDto) {
        Workout newWorkout = workoutService.add(workoutMapper.workoutDtoToWorkout(workoutDto));
        // workoutService.addExercise(newWorkout.getId(), newWorkout.getExercises());
        URI location = URI.create("workout/" + newWorkout.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing workout by given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateWorkout(@PathVariable int id, PutWorkoutDto putWorkoutDto) {
        Workout oldWorkout = workoutService.findById(id);
        Workout updatedWorkout = workoutMapper.putWorkoutDtoToWorkout(id, putWorkoutDto);
        updatedWorkout.setExercises(oldWorkout.getExercises());
        updatedWorkout.setPrograms(oldWorkout.getPrograms());
        updatedWorkout.setGoals(oldWorkout.getGoals());
        Workout updatedWorkoutResponse = workoutService.add(updatedWorkout);
        URI location = URI.create("workouts/" + updatedWorkoutResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete a workout with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable int id) {
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add exercises to a workout with given id")
    @PatchMapping("{id}")
    public ResponseEntity<Void> addExercises(@PathVariable int id,
            @RequestBody Set<Integer> exercises) {
        Workout workoutResponse = workoutService.addExercise(id, workoutMapper.mapExerciseIdsToExercise(exercises));
        URI location = URI.create("workouts/" + workoutResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get all workouts including there exercises")
    @GetMapping("/withexercises")
    public ResponseEntity<Set<GetWorkoutExerciseDto>> getAllWorkoutsWithExercises() {
        Set<GetWorkoutExerciseDto> getWorkoutExerciseDto = workoutService.findAll().stream()
                .map(s -> workoutMapper.workoutToGetWorkoutExerciseDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(getWorkoutExerciseDto);
    }
}
