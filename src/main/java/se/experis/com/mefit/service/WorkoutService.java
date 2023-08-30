package se.experis.com.mefit.service;

import java.util.Set;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.Workout;

public interface WorkoutService extends CrudService<Workout, Integer> {
    Workout addExercise(Integer id, Set<Exercise> exercises);
}
