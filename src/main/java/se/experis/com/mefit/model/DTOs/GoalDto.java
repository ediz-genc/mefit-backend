package se.experis.com.mefit.model.DTOs;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalDto {
    private Integer id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean completed;

    private Integer userId;

    private Set<Integer> programId;

    private Set<Integer> finishedProgramId;

    private Set<Integer> workoutId;

    private Set<Integer> completedWorkoutId;
}
