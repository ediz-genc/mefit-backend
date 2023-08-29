package se.experis.com.mefit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

    private int id;

    private String username;

    private String profilePicUrl;

    private String bio;

    private int weight;

    private double length;

    private Program program;

}
