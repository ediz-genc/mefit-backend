package se.experis.com.mefit.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.service.ProgramService;

@Tag(name = "Programs", description = "Crude and more for programs")
@RestController
@RequestMapping(path = "api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    public ResponseEntity<Set<Program>> getAll() {
        Set<Program> programs = programService.findAll();
        return ResponseEntity.ok(programs);
    }

}
