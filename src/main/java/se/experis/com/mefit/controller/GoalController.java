package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.service.GoalService;

@Tag(name = "Goals", description = "Crud and more for managing goals")
@RestController
@RequestMapping(path = "api/v1/goals")
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @Operation(summary = "Get all goals")
    @GetMapping
    public ResponseEntity<Set<Goal>> getAll() {
        Set<Goal> goals = goalService.findAll();
        return ResponseEntity.ok(goals);
    }

    @Operation(summary = "Gets the goal with the given id")
    @GetMapping("{id}")
    public ResponseEntity<Goal> getById(@PathVariable int id) {
        Goal goal = goalService.findById(id);
        return ResponseEntity.ok(goal);
    }

    @Operation(summary = "Add a new goal")
    @PostMapping
    public ResponseEntity<Goal> addGoal(@PathVariable Goal goal) {
        Goal newGoal = goalService.add(goal);
        URI location = URI.create("goal/" + newGoal.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing goal by given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateGoal(@PathVariable int id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.update(id, goal);
        goalService.add(updatedGoal);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a goal with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable int id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a program to a goal with a given id")
    @PatchMapping("{id}")
    public ResponseEntity<Goal> addProgram(@PathVariable int id, @RequestBody Set<Program> programs) {
        Goal goalResponse = goalService.addPrograms(programs, id);
        URI location = URI.create("goals/" + goalResponse.getId());
        return ResponseEntity.created(location).build();
    }

}
