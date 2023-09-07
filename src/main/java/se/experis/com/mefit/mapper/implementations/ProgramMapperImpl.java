package se.experis.com.mefit.mapper.implementations;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.mapper.abstracts.ProgramMapper;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.DTOs.ProgramDtos.ProgramDto;
import se.experis.com.mefit.model.DTOs.ProgramDtos.PutProgramDto;

@Component
public class ProgramMapperImpl extends ProgramMapper {

    @Override
    public ProgramDto programToProgramDto(Program program) {
        if (program == null) {
            return null;
        }
        ProgramDto programDto = new ProgramDto();

        programDto.setId(program.getId());
        programDto.setName(program.getName());
        programDto.setDescription(program.getDescription());
        programDto.setImgUrl(program.getImgUrl());
        programDto.setCategory(program.getCategory());
        programDto.setGoalId(program.getGoals().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        programDto.setWorkoutId(program.getWorkouts().stream().map(s -> s.getId()).collect(Collectors.toSet()));

        return programDto;
    }

    @Override
    public Program programDtoToProgram(ProgramDto programDto) {
        if (programDto == null) {
            return null;
        }

        Program program = new Program();

        program.setId(programDto.getId());
        program.setName(programDto.getName());
        program.setDescription(programDto.getDescription());
        program.setImgUrl(programDto.getImgUrl());
        program.setCategory(programDto.getCategory());
        program.setGoals(mapGoalIdsToGoals(programDto.getGoalId()));
        program.setWorkouts(mapWorkoutIdsToWorkouts(programDto.getWorkoutId()));

        return program;
    }

    @Override
    public Program putProgramDtoTProgram(PutProgramDto putProgramDto, Integer id) {
        if (putProgramDto == null) {
            return null;
        }

        Program program = new Program();

        program.setId(id);
        program.setName(putProgramDto.getName());
        program.setDescription(putProgramDto.getDescription());
        program.setImgUrl(putProgramDto.getImgUrl());
        program.setCategory(putProgramDto.getCategory());

        return program;
    }

}
