package se.experis.com.mefit.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}
