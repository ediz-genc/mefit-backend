package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.service.WorkoutService;

@Tag(name = "Workout", description = "Crud and more mor workout")
@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Operation(summary = "Get all workouts")
    @GetMapping
    public ResponseEntity<Set<Workout>> getAll() {
        Set<Workout> workouts = workoutService.findAll();
        return ResponseEntity.ok(workouts);
    }

    @Operation(summary = "Get a workout with a given id")
    @GetMapping("{id}")
    public ResponseEntity<Workout> getById(@PathVariable int id) {
        Workout workout = workoutService.findById(id);
        return ResponseEntity.ok(workout);
    }

    @Operation(summary = "Add a new workout")
    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout) {
        Workout newWorkout = workoutService.add(workout);
        URI location = URI.create("workout/" + newWorkout.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing workout by given id")
    @PutMapping("{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable int id, @RequestBody Workout workout) {
        Workout updatedWorkout = workoutService.update(id, workout);
        workoutService.add(updatedWorkout);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a workout with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Workout> deleteWorkout(@PathVariable int id) {
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
