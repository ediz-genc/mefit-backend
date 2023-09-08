package se.experis.com.mefit.model;

import java.util.Set;
// import java.util.stream.Collectors;

// import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {
    public User() {}
    public User(String id) {
        this.id = id;
    }
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private String id;

    @Column(name = "user_username")
    private String username;

    @Column(name = "user_picUrl")
    private String profilePicUrl;

    @Column(name = "user_bio")
    private String bio;

    @Column(name = "user_weight")
    private int weight;

    @Column(name = "user_length")
    private double length;

    @OneToOne
    private Goal currentGoal;

    @OneToMany(mappedBy = "user")
    private Set<Goal> goalHistory;

    // public Set<Goal> getGoalHistory() {
    // return goalHistory;
    // }

    // @JsonGetter("goalHistory")
    // public Set<String> jsonGetGoalHistory() {
    // if (goalHistory != null) {
    // return goalHistory.stream().map(s ->
    // s.getName()).collect(Collectors.toSet());
    // }
    // return null;
    // }

    // public Goal getGoal() {
    // return currentGoal;
    // }

    // @JsonGetter("currentGoal")
    // public String jsonGetCurrentGoal() {
    // if (currentGoal != null) {
    // return currentGoal.getName();
    // }
    // return null;
    // }
    
    @Override
    public String toString() {
        return this.id + " " + this.username + " " + this.profilePicUrl + " " + this.bio + " " + this.weight + " "
                + this.length + " " + this.currentGoal + " " + this.goalHistory;
    }
}
