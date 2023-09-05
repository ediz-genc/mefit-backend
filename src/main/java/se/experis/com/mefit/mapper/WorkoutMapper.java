package se.experis.com.mefit.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.WorkoutDto;
import se.experis.com.mefit.service.ExerciseService;
import se.experis.com.mefit.service.GoalService;
import se.experis.com.mefit.service.ProgramService;

@Mapper(componentModel = "spring")
public abstract class WorkoutMapper {

    @Autowired
    protected ExerciseService exerciseService;
    @Autowired
    protected ProgramService programService;
    @Autowired
    protected GoalService goalService;

    @Mapping(target = "program", source = "program", qualifiedByName = "programIdsToProgram")
    @Mapping(target = "exercise", source = "exercise", qualifiedByName = "exerciseIdsToExercises")
    @Mapping(target = "goal", source = "goal", qualifiedByName = "goalIdsToGoals")

    public abstract WorkoutDto workoutToWorkoutDto(Workout workout);

    @Named("programIdsToPrograms")
    public Set<Program> mapProgramIdsToPrograms(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> programService.findById(s)).collect(Collectors.toSet());
    }

    @Named("goalIdsToGoals")
    public Set<Goal> mapGoalIdsToGoals(Set<Integer> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(s -> goalService.findById(s)).collect(Collectors.toSet());
    }

    @Named("exerciseIdsToExercises")
    public Set<Exercise> mapExerciseIdsToExercise(Set<Integer> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(s -> exerciseService.findById(s)).collect(Collectors.toSet());
    }

    public abstract Workout workoutDtoToWorkout(WorkoutDto workoutDto);

}
