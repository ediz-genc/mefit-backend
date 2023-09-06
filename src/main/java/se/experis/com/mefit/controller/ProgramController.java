package se.experis.com.mefit.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.service.ProgramService;

@CrossOrigin
@Tag(name = "Programs", description = "Crude and more for programs")
@RestController
@RequestMapping(path = "api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @Operation(summary = "Get all programs")
    @GetMapping
    public ResponseEntity<Set<Program>> getAll() {
        Set<Program> programs = programService.findAll();
        return ResponseEntity.ok(programs);
    }

    @Operation(summary = "Get a program by given id")
    @GetMapping("{id}")
    public ResponseEntity<Program> getById(@PathVariable int id) {
        Program program = programService.findById(id);
        return ResponseEntity.ok(program);
    }

    @Operation(summary = "Add new program")
    @PostMapping
    public ResponseEntity<Program> addWorkout(@RequestBody Program program) {
        Program newProgram = programService.add(program);
        URI location = URI.create("/program" + newProgram.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing program with given id")
    @PutMapping("{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable int id, @RequestBody Program program) {
        Program updatedProgram = programService.update(id, program);
        programService.add(updatedProgram);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete program with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Program> deleteProgram(@PathVariable int id) {
        programService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add workouts to a program with a given id")
    @PatchMapping("{id}")
    public ResponseEntity<Program> addWorkouts(@PathVariable int id, @RequestBody Set<Workout> workouts) {
        Program programResponse = programService.addWorkOut(workouts, id);
        URI location = URI.create("programs/" + programResponse.getId());
        return ResponseEntity.created(location).build();
    }

}
