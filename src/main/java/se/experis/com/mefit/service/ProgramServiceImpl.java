package se.experis.com.mefit.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.repository.ProgramRepository;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program findById(Integer id) {
        return programRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Program> findAll() {
        return Set.copyOf(programRepository.findAll());
    }

    @Override
    public Program add(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Program update(Integer id, Program program) {
        return programRepository.save(program);
    }

    @Override
    public void deleteById(Integer id) {
        programRepository.deleteById(id);
    }

    @Override
    public Program addWorkOut(Set<Workout> workouts, Integer id) {
        Program program = programRepository.findById(id).get();

        Set<Workout> workoutList = program.getWorkouts();

        for (Workout workout : workouts) {
            workoutList.add(workout);
        }
        program.setWorkouts(workouts);
        programRepository.save(program);
        return program;
    }

}
