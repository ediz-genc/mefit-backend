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
import se.experis.com.mefit.mapper.abstracts.ProgramMapper;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.DTOs.ProgramDtos.ProgramDto;
import se.experis.com.mefit.model.DTOs.ProgramDtos.PutProgramDto;
import se.experis.com.mefit.service.interfaces.ProgramService;

@CrossOrigin
@Tag(name = "Programs", description = "Crude and more for programs")
@RestController
@RequestMapping(path = "api/v1/programs")
public class ProgramController {
    private final ProgramService programService;
    private final ProgramMapper programMapper;

    @Autowired
    public ProgramController(ProgramService programService, ProgramMapper programMapper) {
        this.programService = programService;
        this.programMapper = programMapper;
    }

    @Operation(summary = "Get all programs")
    @GetMapping
    public ResponseEntity<Set<ProgramDto>> getAll() {
        Set<ProgramDto> programDtos = programService.findAll().stream().map(s -> programMapper.programToProgramDto(s))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(programDtos);
    }

    @Operation(summary = "Get a program by given id")
    @GetMapping("{id}")
    public ResponseEntity<ProgramDto> getById(@PathVariable int id) {
        ProgramDto programDto = programMapper.programToProgramDto(programService.findById(id));
        return ResponseEntity.ok(programDto);
    }

    @Operation(summary = "Add new program")
    @PostMapping
    public ResponseEntity<Program> addWorkout(@RequestBody ProgramDto programDto) {
        Program newProgram = programService.add(programMapper.programDtoToProgram(programDto));
        URI location = URI.create("/program" + newProgram.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing program with given id")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateProgram(@PathVariable int id, PutProgramDto putProgramDto) {
        Program oldProgram = programService.findById(id);
        Program updatedProgram = programMapper.putProgramDtoTProgram(putProgramDto, id);
        updatedProgram.setGoals(oldProgram.getGoals());
        updatedProgram.setWorkouts(oldProgram.getWorkouts());
        Program updatedProgramResponse = programService.add(updatedProgram);
        URI location = URI.create("programs/" + updatedProgramResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete program with given id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable int id) {
        programService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add workouts to a program with a given id")
    @PatchMapping("{id}")
    public ResponseEntity<Void> addWorkouts(@PathVariable int id, @RequestBody Set<Integer> workoutIds) {
        Program programResponse = programService.addWorkout(programMapper.mapWorkoutIdsToWorkouts(workoutIds), id);
        URI location = URI.create("programs/" + programResponse.getId());
        return ResponseEntity.created(location).build();
    }

}
