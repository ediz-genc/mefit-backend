package se.experis.com.mefit.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.service.ExerciseService;

public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<Set<Exercise>> getAll() {
        Set<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }
}
