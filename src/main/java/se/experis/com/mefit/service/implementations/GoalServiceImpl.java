package se.experis.com.mefit.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.repository.GoalRepository;
import se.experis.com.mefit.service.interfaces.GoalService;

@Service
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

    @Override
    public Set<Workout> getCompletedWorkouts(Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Workout> completedWorkouts = goal.getCompletedWorkouts();
        return completedWorkouts;
    }

    @Override
    public Set<Workout> getWorkouts(Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Workout> workouts = goal.getWorkouts();
        return workouts;
    }

    @Override
    public Set<Program> getCompletedPrograms(Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Program> completedPrograms = goal.getCompletedPrograms();
        return completedPrograms;
    }

    @Override
    public Set<Program> getPrograms(Integer id) {
        Goal goal = goalRepository.findById(id).get();

        Set<Program> programs = goal.getPrograms();
        return programs;
    }

    @Override
    public Goal completeWorkout(Integer id, Integer workoutId) {
        Goal goal = goalRepository.findById(id).get();

        Set<Workout> completedWorkouts = goal.getCompletedWorkouts();
        Set<Workout> workouts = goal.getWorkouts();

        for (Workout workout : workouts) {
            if (workout.getId() == workoutId) {
                completedWorkouts.add(workout);
                workouts.remove(workout);
                break;
            }
        }

        goal.setCompletedWorkouts(completedWorkouts);
        goal.setWorkouts(workouts);
        goalRepository.save(goal);
        return goal;
    }

    @Override
    public Goal completeProgram(Integer id, Integer programId) {
        Goal goal = goalRepository.findById(id).get();

        Set<Program> completedPrograms = goal.getCompletedPrograms();
        Set<Program> programs = goal.getPrograms();

        for (Program program : programs) {
            if (program.getId() == programId) {
                completedPrograms.add(program);
                programs.remove(program);
                break;
            }
        }

        goal.setCompletedPrograms(completedPrograms);
        goal.setPrograms(programs);
        goalRepository.save(goal);
        return goal;
    }

}
