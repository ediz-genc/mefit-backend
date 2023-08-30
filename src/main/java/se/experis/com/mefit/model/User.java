package se.experis.com.mefit.model;

import java.util.Set;

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
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

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

    @ManyToMany(mappedBy = "user")
    private Set<Goal> goal;

}
