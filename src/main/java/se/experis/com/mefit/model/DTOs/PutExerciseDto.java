package se.experis.com.mefit.model.DTOs;

import lombok.Getter;
import lombok.Setter;
import se.experis.com.mefit.model.FitnessLevel;

@Getter
@Setter
public class PutExerciseDto {

    private String name;

    private String description;

    private String muscleGroup;

    private String imgUrl;

    private String vidUrl;

    private FitnessLevel fitnessLevel;
}
