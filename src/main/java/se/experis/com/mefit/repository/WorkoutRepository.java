package se.experis.com.mefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.experis.com.mefit.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

}
