package se.experis.com.mefit.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.repository.GoalRepository;

public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal findById(Integer id) {
        return goalRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Goal> findAll() {
        return Set.copyOf(goalRepository.findAll());
    }

    @Override
    public Goal add(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Goal update(Integer id, Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void deleteById(Integer id) {
        goalRepository.deleteById(id);
    }

    @Override
    public Goal addPrograms(Set<Program> programs, Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Program> programList = goal.getPrograms();

        for (Program program : programs) {
            programList.add(program);
        }
        goal.setPrograms(programs);
        goalRepository.save(goal);
        return goal;
    }

    @Override
    public Goal addWorkouts(Set<Workout> workouts, Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Workout> workoutList = goal.getWorkouts();

        for (Workout workout : workouts) {
            workoutList.add(workout);
        }
        goal.setWorkouts(workouts);
        goalRepository.save(goal);
        return goal;
    }

}
