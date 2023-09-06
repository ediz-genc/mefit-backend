package se.experis.com.mefit.model.DTOs;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutGoalDto {

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean completed;

}
