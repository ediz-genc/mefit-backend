package se.experis.com.mefit.model.DTOs.WorkoutDtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import se.experis.com.mefit.model.Exercise;

@Getter
@Setter
public class GetWorkoutExerciseDto {

    private Integer id;

    private String name;

    private String description;

    private Set<Exercise> exercises;
}
