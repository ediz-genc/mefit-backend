package se.experis.com.mefit.model.DTOs;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import se.experis.com.mefit.model.FitnessLevel;

@Getter
@Setter
public class ExerciseDto {
    private Integer id;

    private String name;

    private String description;

    private String muscleGroup;

    private String imgUrl;

    private String vidUrl;

    private FitnessLevel fitnessLevel;

    private Set<Integer> workoutId;
}
