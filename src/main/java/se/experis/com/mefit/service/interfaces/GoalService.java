package se.experis.com.mefit.service.interfaces;

import java.util.Set;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.service.CrudService;

public interface GoalService extends CrudService<Goal, Integer> {
    Goal addPrograms(Set<Program> programs, Integer id);

    Goal addWorkouts(Set<Workout> workouts, Integer id);

    Set<Workout> getCompletedWorkouts(Integer id);

    Set<Workout> getWorkouts(Integer id);
}
