package se.experis.com.mefit.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.ProgramDto;
import se.experis.com.mefit.service.GoalService;
import se.experis.com.mefit.service.ProgramService;
import se.experis.com.mefit.service.WorkoutService;

@Mapper(componentModel = "spring")
public abstract class ProgramMapper {
    @Autowired
    protected ProgramService programService;
    @Autowired
    protected GoalService goalService;
    @Autowired
    protected WorkoutService workoutService;

    @Mapping(target = "workout", source = "workout", qualifiedByName = "workoutIdsToWorkouts")
    @Mapping(target = "goal", source = "goal", qualifiedByName = "goalIdsToGoals")

    public abstract ProgramDto programToProgramDto(Program program);

    @Named("workoutIdsToWorkouts")
    public Set<Workout> mapWorkoutIdsToWorkouts(Set<Integer> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(s -> workoutService.findById(s)).collect(Collectors.toSet());
    }

    @Named("goalIdsToGoals")
    public Set<Goal> mapGoalIdsToGoals(Set<Integer> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(s -> goalService.findById(s)).collect(Collectors.toSet());
    }

    public abstract Program programDtoToProgram(ProgramDto programDto);

    public abstract Program patchProgramDtoTProgram(ProgramDto programDto, Integer id);

}
