package se.experis.com.mefit.model.DTOs;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private int id;

    private String username;

    private String profilePicUrl;

    private String bio;

    private int weight;

    private double length;

    private Integer currentGoalId;

    private Set<Integer> goalHistoryId;
}
