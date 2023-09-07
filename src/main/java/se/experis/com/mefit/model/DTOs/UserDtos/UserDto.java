package se.experis.com.mefit.model.DTOs.UserDtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String keyId;

    private String username;

    private String profilePicUrl;

    private String bio;

    private int weight;

    private double length;

    private Integer currentGoalId;

    private Set<Integer> goalHistoryId;
}
