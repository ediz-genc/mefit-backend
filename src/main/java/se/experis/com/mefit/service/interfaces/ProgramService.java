package se.experis.com.mefit.service.interfaces;

import java.util.Set;

import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.service.CrudService;

public interface ProgramService extends CrudService<Program, Integer> {
    Program addWorkout(Set<Workout> workouts, Integer id);
}
