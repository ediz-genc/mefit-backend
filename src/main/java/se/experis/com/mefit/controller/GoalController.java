package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.HashSet;
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
import se.experis.com.mefit.mapper.abstracts.GoalMapper;
import se.experis.com.mefit.mapper.abstracts.ProgramMapper;
import se.experis.com.mefit.mapper.abstracts.WorkoutMapper;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.GoalDtos.GoalDto;
import se.experis.com.mefit.model.DTOs.GoalDtos.PutGoalDto;
import se.experis.com.mefit.model.DTOs.ProgramDtos.ProgramDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.WorkoutDto;
import se.experis.com.mefit.service.interfaces.GoalService;

@CrossOrigin
@Tag(name = "Goals", description = "Crud and more for managing goals")
@RestController
@RequestMapping(path = "api/v1/goals")
public class GoalController {
    private final GoalService goalService;
    private final GoalMapper goalMapper;
    private final WorkoutMapper workoutMapper;
    private final ProgramMapper programMapper;

    @Autowired
    public GoalController(GoalService goalService, GoalMapper goalMapper, WorkoutMapper workoutMapper,
            ProgramMapper programMapper) {
        this.goalService = goalService;
        this.goalMapper = goalMapper;
        this.workoutMapper = workoutMapper;
        this.programMapper = programMapper;
    }

    @Operation(summary = "Get all goals")
    @GetMapping
    public ResponseEntity<Set<GoalDto>> getAll() {
        Set<GoalDto> goalDtos = goalService.findAll().stream().map(s -> goalMapper.goalToGoalDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(goalDtos);
    }

    @Operation(summary = "Gets the goal with the given id")
    @GetMapping("{id}")
    public ResponseEntity<GoalDto> getById(@PathVariable int id) {
        GoalDto goalDto = goalMapper.goalToGoalDto(goalService.findById(id));
        return ResponseEntity.ok(goalDto);
    }

    @Operation(summary = "Add a new goal")
    @PostMapping
    public ResponseEntity<Goal> addGoal(@PathVariable GoalDto goalDto) {
        Goal newGoal = goalService.add(goalMapper.goalDtoToGoal(goalDto));
        URI location = URI.create("goal/" + newGoal.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing goal by given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateGoal(@PathVariable int id, PutGoalDto putGoalDto) {
        Goal oldGoal = goalService.findById(id);
        Goal updatedGoal = goalMapper.putGoalDtoToGoal(putGoalDto, id);
        updatedGoal.setPrograms(oldGoal.getPrograms());
        updatedGoal.setUser(oldGoal.getUser());
        updatedGoal.setWorkouts(oldGoal.getWorkouts());
        Goal updatedGoalResponse = goalService.add(updatedGoal);
        URI location = URI.create("goals/" + updatedGoalResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete a goal with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable int id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add programs to a goal with a given id")
    @PatchMapping("{id}/program")
    public ResponseEntity<Void> addPrograms(@PathVariable int id, @RequestBody Set<Integer> programIds) {
        Goal goalResponse = goalService.addPrograms(goalMapper.mapProgramIdsToPrograms(programIds), id);
        URI location = URI.create("goals/" + goalResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Add workouts to a goal with a given id")
    @PatchMapping("{id}/workout")
    public ResponseEntity<Void> addWorkouts(@PathVariable int id, @RequestBody Set<Integer> workoutIds) {
        Goal goalResponse = goalService.addWorkouts(goalMapper.mapWorkoutIdsToWorkouts(workoutIds), id);
        URI location = URI.create("goals/" + goalResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get all completed workouts in a goal whit given id")
    @GetMapping("{id}/workouts/completed")
    public ResponseEntity<Set<WorkoutDto>> getCompletedWorkouts(@PathVariable int id) {
        Set<Workout> completedWorkouts = goalService.getCompletedWorkouts(id);
        Set<WorkoutDto> completedWorkoutsDtos = completedWorkouts.stream()
                .map(s -> workoutMapper.workoutToWorkoutDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(completedWorkoutsDtos);
    }

    @Operation(summary = "Get all pending workouts in a goal with given id")
    @GetMapping("{id}/workouts/pending")
    public ResponseEntity<Set<WorkoutDto>> getPendingWorkouts(@PathVariable int id) {
        Set<Workout> pendingWorkouts = goalService.getWorkouts(id);
        Set<WorkoutDto> pendingWorkoutDtos = pendingWorkouts.stream().map(s -> workoutMapper.workoutToWorkoutDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(pendingWorkoutDtos);
    }

    @Operation(summary = "Get all workouts in a goal with a given id")
    @GetMapping("{id}/workouts")
    public ResponseEntity<Set<WorkoutDto>> getAllWorkouts(@PathVariable int id) {
        Set<Workout> pending = goalService.getCompletedWorkouts(id);
        Set<Workout> completed = goalService.getWorkouts(id);
        Set<Workout> all = new HashSet<>();

        all.addAll(pending);
        all.addAll(completed);

        Set<WorkoutDto> dtos = all.stream().map(s -> workoutMapper.workoutToWorkoutDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Get all completed programs in a goal with given id")
    @GetMapping("{id}/programs/completed")
    public ResponseEntity<Set<ProgramDto>> getCompletedPrograms(@PathVariable int id) {
        Set<Program> completedPrograms = goalService.getCompletedPrograms(id);
        Set<ProgramDto> completedProgramDtos = completedPrograms.stream().map(s -> programMapper.programToProgramDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(completedProgramDtos);
    }

    @Operation(summary = "Get all pending programs in a goal with given id")
    @GetMapping("{id}/programs/pending")
    public ResponseEntity<Set<ProgramDto>> getPendingPrograms(@PathVariable int id) {
        Set<Program> pendingPrograms = goalService.getPrograms(id);
        Set<ProgramDto> pendingProgramDtos = pendingPrograms.stream().map(s -> programMapper.programToProgramDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(pendingProgramDtos);
    }

    @Operation(summary = "Get all programs in a goal with given id")
    @GetMapping("{id}/programs")
    public ResponseEntity<Set<ProgramDto>> getAllPrograms(@PathVariable int id) {
        Set<Program> pending = goalService.getCompletedPrograms(id);
        Set<Program> completed = goalService.getPrograms(id);
        Set<Program> all = new HashSet<>();

        all.addAll(pending);
        all.addAll(completed);

        Set<ProgramDto> dtos = all.stream().map(s -> programMapper.programToProgramDto(s)).collect(Collectors.toSet());
        return ResponseEntity.ok(dtos);
    }
}
