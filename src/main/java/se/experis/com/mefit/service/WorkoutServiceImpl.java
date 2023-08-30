package se.experis.com.mefit.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.repository.WorkoutRepository;

public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout findById(Integer id) {
        return workoutRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Workout> findAll() {
        return Set.copyOf(workoutRepository.findAll());

    }

    @Override
    public Workout add(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public Workout update(Integer id, Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public void deleteById(Integer id) {
        workoutRepository.deleteById(id);
    }

    @Override
    public Workout addExercise(Integer id, Set<Exercise> exercises) {
        Workout workout = workoutRepository.findById(id).get();

        Set<Exercise> exerciseList = workout.getExercises();

        for (Exercise exercise : exercises) {
            exerciseList.add(exercise);
        }
        workout.setExercises(exercises);
        workoutRepository.save(workout);
        return workout;

    }

}
