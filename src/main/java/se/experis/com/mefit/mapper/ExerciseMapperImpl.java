package se.experis.com.mefit.mapper;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.DTOs.ExerciseDto;

public class ExerciseMapperImpl extends ExerciseMapper {

    @Override
    public ExerciseDto exerciseDto(Exercise exercise) {
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
    public Exercise movieDtoToExercise(ExerciseDto exerciseDto) {
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

}
