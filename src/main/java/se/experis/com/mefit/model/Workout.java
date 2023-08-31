package se.experis.com.mefit.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tb_workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private int id;

    @Column(name = "workout_name")
    private String name;

    @Column(name = "workout_desc")
    private String description;

    @ManyToMany(mappedBy = "workouts")
    private Set<Exercise> exercises;

    @ManyToMany
    private Set<Program> programs;

    @ManyToMany
    private Set<Goal> goals;

    public Set<Goal> getGoals() {
        return goals;
    }

    @JsonGetter("goals")
    public Set<String> jsonGetGoals() {
        if (goals != null) {
            return goals.stream().map(s -> s.getName()).collect(Collectors.toSet());
        }
        return null;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    @JsonGetter("programs")
    public Set<String> jsonGetProgram() {
        if (programs != null) {
            return programs.stream().map(s -> s.getName()).collect(Collectors.toSet());
        }
        return null;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    @JsonGetter("exercises")
    public Set<String> jsonGetExercises() {
        if (exercises != null) {
            return exercises.stream().map(s -> s.getName()).collect(Collectors.toSet());
        }
        return null;
    }

}
