package se.experis.com.mefit.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.Program;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.Workout;
import se.experis.com.mefit.model.DTOs.GoalDto;
import se.experis.com.mefit.model.DTOs.PutGoalDto;
import se.experis.com.mefit.service.GoalService;
import se.experis.com.mefit.service.ProgramService;
import se.experis.com.mefit.service.UserService;
import se.experis.com.mefit.service.WorkoutService;

@Mapper(componentModel = "spring")
public abstract class GoalMapper {

    @Autowired
    protected GoalService goalService;
    @Autowired
    protected WorkoutService workoutService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ProgramService programService;

    @Mapping(target = "users", source = "users", qualifiedByName = "userIdsToUsers")
    @Mapping(target = "programs", source = "programs", qualifiedByName = "programIdsToPrograms")
    @Mapping(target = "workouts", source = "workouts", qualifiedByName = "workoutIdsToWorkouts")

    public abstract GoalDto goalToGoalDto(Goal goal);

    @Named("userIdsToUsers")
    public Set<User> mapUserIdsToUsers(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> userService.findById(s)).collect(Collectors.toSet());
    }

    @Named("programIdsToPrograms")
    public Set<Program> mapProgramIdsToPrograms(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> programService.findById(s)).collect(Collectors.toSet());
    }

    @Named("workoutIdsToWorkouts")
    public Set<Workout> mapWorkoutIdsToWorkouts(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> workoutService.findById(s)).collect(Collectors.toSet());
    }

    @Named("mapUserIdToUser")
    public User mapUserIdToUser(Integer id) {
        if (id == null) {
            return null;
        }
        return userService.findById(id);
    }

    public abstract Goal goalDtoToGoal(GoalDto goalDto);

    public abstract Goal putGoalDtoToGoal(PutGoalDto putGoalDto, Integer id);

}
