package se.experis.com.mefit.model.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUserDto {

    private String username;

    private String profilePicUrl;

    private String vidUrl;

    private String bio;

    private int weight;

    private double length;
}
