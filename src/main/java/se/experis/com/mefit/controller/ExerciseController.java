package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.mapper.abstracts.ExerciseMapper;
import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.DTOs.ExerciseDtos.ExerciseDto;
import se.experis.com.mefit.model.DTOs.ExerciseDtos.PutExerciseDto;
import se.experis.com.mefit.service.interfaces.ExerciseService;

@Tag(name = "Exercise", description = "Crud and more for exercises")
@RestController
@RequestMapping(path = "api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }

    @Operation(summary = "Get all exercises")
    @GetMapping
    public ResponseEntity<Set<ExerciseDto>> getAll() {
        Set<ExerciseDto> exerciseDtos = exerciseService.findAll().stream()
                .map(s -> exerciseMapper.exerciseToExerciseDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(exerciseDtos);
    }

    @Operation(summary = "Get exercise with given id")
    @GetMapping("{id}")
    public ResponseEntity<ExerciseDto> getById(@PathVariable int id) {
        ExerciseDto exerciseDto = exerciseMapper.exerciseToExerciseDto(exerciseService.findById(id));
        return ResponseEntity.ok(exerciseDto);
    }

    @Operation(summary = "Add a new exercise")
    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody ExerciseDto exerciseDto) {
        Exercise newExercise = exerciseService.add(exerciseMapper.exerciseDtoToExercise(exerciseDto));
        URI location = URI.create("/exercise" + newExercise.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an exercise with given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateExercise(@PathVariable int id, PutExerciseDto putExerciseDto) {
        Exercise oldExercise = exerciseService.findById(id);
        Exercise updatedExercise = exerciseMapper.putExerciseDtoToExercise(putExerciseDto, id);
        updatedExercise.setId(id);
        updatedExercise.setWorkouts(oldExercise.getWorkouts());
        exerciseService.add(updatedExercise);
        URI location = URI.create("exercises/" + updatedExercise.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete an exercise with a given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable int id) {
        exerciseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
