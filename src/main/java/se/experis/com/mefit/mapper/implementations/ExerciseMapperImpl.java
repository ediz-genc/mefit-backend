package se.experis.com.mefit.mapper.implementations;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.mapper.abstracts.ExerciseMapper;
import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.DTOs.ExerciseDtos.ExerciseDto;
import se.experis.com.mefit.model.DTOs.ExerciseDtos.PutExerciseDto;

@Component
public class ExerciseMapperImpl extends ExerciseMapper {

    @Override
    public ExerciseDto exerciseToExerciseDto(Exercise exercise) {
        if (exercise == null) {
            return null;
        }
        ExerciseDto exerciseDto = new ExerciseDto();

        exerciseDto.setId(exercise.getId());
        exerciseDto.setName(exercise.getName());
        exerciseDto.setDescription(exercise.getDescription());
        exerciseDto.setMuscleGroup(exercise.getMuscleGroup());
        exerciseDto.setImgUrl(exercise.getImgUrl());
        exerciseDto.setVidUrl(exercise.getVidUrl());
        exerciseDto.setFitnessLevel(exercise.getFitnessLevel());

        return exerciseDto;
    }

    @Override
    public Exercise exerciseDtoToExercise(ExerciseDto exerciseDto) {
        if (exerciseDto == null) {
            return null;
        }
        Exercise exercise = new Exercise();

        exercise.setId(exerciseDto.getId());
        exercise.setName(exerciseDto.getName());
        exercise.setDescription(exerciseDto.getDescription());
        exercise.setMuscleGroup(exerciseDto.getMuscleGroup());
        exercise.setImgUrl(exerciseDto.getImgUrl());
        exercise.setVidUrl(exerciseDto.getVidUrl());
        exercise.setFitnessLevel(exerciseDto.getFitnessLevel());

        return exercise;
    }

    @Override
    public Exercise putExerciseDtoToExercise(PutExerciseDto putExerciseDto, Integer id) {
        if (putExerciseDto == null) {
            return null;
        }
        Exercise exercise = new Exercise();

        exercise.setName(putExerciseDto.getName());
        exercise.setDescription(putExerciseDto.getDescription());
        exercise.setMuscleGroup(putExerciseDto.getMuscleGroup());
        exercise.setImgUrl(putExerciseDto.getImgUrl());
        exercise.setVidUrl(putExerciseDto.getVidUrl());
        exercise.setFitnessLevel(putExerciseDto.getFitnessLevel());

        return exercise;
    }

}
