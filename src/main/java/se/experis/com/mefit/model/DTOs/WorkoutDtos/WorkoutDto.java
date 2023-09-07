package se.experis.com.mefit.model.DTOs.WorkoutDtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutDto {
    private Integer id;

    private String name;

    private String description;

    private Set<Integer> exerciseId;

    private Set<Integer> programId;

    private Set<Integer> goalId;
}
