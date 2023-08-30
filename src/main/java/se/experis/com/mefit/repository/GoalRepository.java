package se.experis.com.mefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.experis.com.mefit.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

}
