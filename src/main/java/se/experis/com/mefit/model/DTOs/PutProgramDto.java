package se.experis.com.mefit.model.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutProgramDto {

    private String name;

    private String description;

    private String imgUrl;

    private String category;

}
