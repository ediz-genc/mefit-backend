package se.experis.com.mefit.model;

import java.time.LocalDate;
import java.util.Set;
// import java.util.stream.Collectors;

// import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Integer id;

    @Column(name = "goal_name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "completed")
    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(joinColumns = @JoinColumn(name = "goal_id"), inverseJoinColumns = @JoinColumn(name = "program_id"))
    private Set<Program> programs;

    @ManyToMany
    private Set<Program> completedPrograms;

    @ManyToMany()
    @JoinTable(joinColumns = @JoinColumn(name = "goal_id"), inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private Set<Workout> workouts;

    @ManyToMany
    private Set<Workout> completedWorkouts;

    // public Set<Program> getPrograms() {
    // return programs;
    // }

    // @JsonGetter("programs")
    // public Set<String> jsonGetPrograms() {
    // if (programs != null) {
    // return programs.stream().map(s -> s.getName()).collect(Collectors.toSet());
    // }
    // return null;
    // }

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

    // public User getUser() {
    // return user;
    // }

    // @JsonGetter("user")
    // public String jsonGetUser() {
    // if (user != null) {
    // return user.getUsername();
    // }
    // return null;
    // }
}
