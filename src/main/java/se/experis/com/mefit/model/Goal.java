package se.experis.com.mefit.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_goal")
public class Goal {

    @Id
    @Column(name = "goal_id")
    private int id;

    @Column(name = "goal_name")
    private String name;

    @OneToMany()
    private User user;

    private Set<Program> programs;

    private Set<Exercise> exercises;
}
