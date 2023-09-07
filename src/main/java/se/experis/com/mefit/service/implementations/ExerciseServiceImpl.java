package se.experis.com.mefit.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.experis.com.mefit.model.Exercise;
import se.experis.com.mefit.repository.ExerciseRepository;
import se.experis.com.mefit.service.interfaces.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Exercise> findAll() {
        return Set.copyOf(exerciseRepository.findAll());
    }

    @Override
    public Exercise add(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise update(Integer id, Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public void deleteById(Integer id) {
        exerciseRepository.deleteById(id);
    }
}
