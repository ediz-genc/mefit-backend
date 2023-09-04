package se.experis.com.mefit.mapper;

import java.util.stream.Collectors;

import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.WorkoutDto;

public class WorkoutMapperImpl extends WorkoutMapper {

    @Override
    public WorkoutDto workoutToWorkoutDto(Workout workout) {
        if (workout == null) {
            return null;
        }

        WorkoutDto workoutDto = new WorkoutDto();

        workoutDto.setId(workout.getId());
        workoutDto.setName(workout.getName());
        workoutDto.setDescription(workout.getDescription());
        workoutDto.setExerciseId(workout.getExercises().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        workoutDto.setProgramId(workout.getPrograms().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        workoutDto.setGoalId(workout.getGoals().stream().map(s -> s.getId()).collect(Collectors.toSet()));

        return workoutDto;
    }

    @Override
    public Workout exerciseDtoToExercise(WorkoutDto workoutDto) {
        if (workoutDto == null) {
            return null;
        }

        Workout workout = new Workout();

        workout.setId(workoutDto.getId());
        workout.setName(workoutDto.getName());
        workout.setDescription(workoutDto.getDescription());
        workout.setExercises(mapExerciseIdsToExercise(workoutDto.getExerciseId()));
        workout.setPrograms(mapProgramIdsToPrograms(workoutDto.getProgramId()));
        workout.setGoals(mapGoalIdsToGoals(workoutDto.getGoalId()));

        return workout;
    }

}
