package se.experis.com.mefit.model;

import java.util.Set;
// import java.util.stream.Collectors;

// import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id", nullable = false)
    private int id;

    @Column(name = "exercise_name", nullable = false, length = 50)
    private String name;

    @Column(name = "exercise_desc", length = 200)
    private String description;

    @Column(name = "muscle_group", length = 50)
    private String muscleGroup;

    @Column(name = "exercise_img_url")
    private String imgUrl;

    @Column(name = "exercise_vid_url")
    private String vidUrl;

    @Column(name = "fitness_lvl")
    @Enumerated(EnumType.STRING)
    private FitnessLevel fitnessLevel;

    @ManyToMany
    private Set<Workout> workouts;

    // public Set<Workout> getWorkouts() {
    // return workouts;
    // }

    // @JsonGetter("workouts")
    // public Set<String> jsonGetWorkouts() {
    // if (workouts != null) {
    // return workouts.stream().map(s -> s.getName()).collect(Collectors.toSet());
    // }
    // return null;
    // }

}
