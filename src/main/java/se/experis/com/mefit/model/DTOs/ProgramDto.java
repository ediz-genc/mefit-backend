package se.experis.com.mefit.model.DTOs;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramDto {
    private int id;

    private String name;

    private String description;

    private String imgUrl;

    private String category;

    private Set<Integer> workoutId;

    private Set<Integer> goalId;
}
