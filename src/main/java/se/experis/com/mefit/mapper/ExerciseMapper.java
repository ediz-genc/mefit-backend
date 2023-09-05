package se.experis.com.mefit.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.ExerciseDto;
import se.experis.com.mefit.service.ExerciseService;
import se.experis.com.mefit.service.WorkoutService;

@Mapper(componentModel = "spring")
public abstract class ExerciseMapper {
    @Autowired
    protected ExerciseService exerciseService;
    @Autowired
    protected WorkoutService workoutService;

    @Mapping(target = "workouts", source = "workouts", qualifiedByName = "workoutIdsToWorkouts")
    public abstract ExerciseDto exerciseToExerciseDto(Exercise exercise);

    @Named("workoutIdsToWorkouts")
    public Set<Workout> mapWorkoutIdsToWorkouts(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> workoutService.findById(s)).collect(Collectors.toSet());
    }

    public abstract Exercise exerciseDtoToExercise(ExerciseDto exerciseDto);

    public abstract Exercise patchExerciseDtoToExercise(ExerciseDto exerciseDto, Integer id);
}
