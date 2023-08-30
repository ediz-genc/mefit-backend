package se.experis.com.mefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.experis.com.mefit.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

}
