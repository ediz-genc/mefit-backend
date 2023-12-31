package se.experis.com.mefit.mapper.implementations;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.mapper.abstracts.WorkoutMapper;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.GetWorkoutExerciseDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.PutWorkoutDto;
import se.experis.com.mefit.model.DTOs.WorkoutDtos.WorkoutDto;

@Component
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
    public Workout workoutDtoToWorkout(WorkoutDto workoutDto) {
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

    @Override
    public Workout putWorkoutDtoToWorkout(Integer id, PutWorkoutDto putWorkoutDto) {
        if (putWorkoutDto == null) {
            return null;
        }

        Workout workout = new Workout();

        workout.setId(id);
        workout.setName(putWorkoutDto.getName());
        workout.setDescription(putWorkoutDto.getDescription());

        return workout;
    }

    @Override
    public GetWorkoutExerciseDto workoutToGetWorkoutExerciseDto(Workout workout) {
        if (workout == null) {
            return null;
        }

        GetWorkoutExerciseDto getWorkoutExerciseDto = new GetWorkoutExerciseDto();

        getWorkoutExerciseDto.setId(workout.getId());
        getWorkoutExerciseDto.setName(workout.getName());
        getWorkoutExerciseDto.setDescription(workout.getDescription());
        getWorkoutExerciseDto.setExercises(workout.getExercises());

        return getWorkoutExerciseDto;
    }

}
