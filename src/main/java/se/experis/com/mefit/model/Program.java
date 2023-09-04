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
@Table(name = "tb_program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private int id;

    @Column(name = "program_name")
    private String name;

    @Column(name = "program_desc")
    private String description;

    @Column(name = "program_img_url")
    private String imgUrl;

    @Column(name = "program_category")
    private String category;

    @ManyToMany(mappedBy = "programs")
    private Set<Workout> workouts;

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

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    @JsonGetter("workouts")
    public Set<String> jsonGetWorkouts() {
        if (workouts != null) {
            return workouts.stream().map(s -> s.getName()).collect(Collectors.toSet());
        }
        return null;
    }

}
