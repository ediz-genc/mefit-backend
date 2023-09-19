package se.experis.com.mefit.model.DTOs.UserDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUserDto {

    private String username;

    private String profilePicUrl;

    private String bio;

    private Integer weight;

    private double length;
}
