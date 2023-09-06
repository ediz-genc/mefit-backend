package se.experis.com.mefit.service;

import java.util.Set;

import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;

public interface ProgramService extends CrudService<Program, Integer> {
    Program addWorkout(Set<Workout> workouts, Integer id);
}
