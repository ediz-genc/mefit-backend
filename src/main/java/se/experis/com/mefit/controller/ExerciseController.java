package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.service.ExerciseService;

@Tag(name = "Exercise", description = "Crud and more for exercises")
@RestController
@RequestMapping(path = "api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Operation(description = "Get all exercises")
    @GetMapping
    public ResponseEntity<Set<Exercise>> getAll() {
        Set<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }

    @Operation(description = "Get exercise with given id")
    @GetMapping("{id}")
    public ResponseEntity<Exercise> getById(@PathVariable int id) {
        Exercise exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exercise);
    }

    @Operation(description = "Add a new exercise")
    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        Exercise newExercise = exerciseService.add(exercise);
        URI location = URI.create("/exercise" + newExercise.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(description = "Delete an exercise with a given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable int id) {
        exerciseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
