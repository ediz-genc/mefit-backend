package se.experis.com.mefit.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_workout")
public class Workout {

    @Column(name = "workout_id")
    private int id;

    @Column(name = "workout_name")
    private String name;

    @Column(name = "workout_name")
    private String description;

    @ManyToMany(mappedBy = "workout")
    private Set<Exercise> exercises;

}
