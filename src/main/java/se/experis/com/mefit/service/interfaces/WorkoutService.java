package se.experis.com.mefit.service.interfaces;

import java.util.Set;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.service.CrudService;

public interface WorkoutService extends CrudService<Workout, Integer> {
    Workout addExercise(Integer id, Set<Exercise> exercises);
}
