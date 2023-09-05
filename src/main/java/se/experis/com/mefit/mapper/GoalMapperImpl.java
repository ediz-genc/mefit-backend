package se.experis.com.mefit.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.DTOs.GoalDto;

@Component
public class GoalMapperImpl extends GoalMapper {

    @Override
    public GoalDto goalToGoalDto(Goal goal) {

        if (goal == null) {
            return null;
        }
        GoalDto goalDto = new GoalDto();

        goalDto.setId(goal.getId());
        goalDto.setName(goal.getName());
        goalDto.setStartDate(goal.getStartDate());
        goalDto.setEndDate(goal.getEndDate());
        goalDto.setCompleted(goal.getCompleted());
        goalDto.setUserId(goal.getUser().getId());
        goalDto.setProgramId(goal.getPrograms().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        goalDto.setWorkoutId(goal.getWorkouts().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        return goalDto;
    }

    @Override
    public Goal goalDtoToGoal(GoalDto goalDto) {
        if (goalDto == null) {
            return null;
        }
        Goal goal = new Goal();

        goal.setId(goalDto.getId());
        goal.setName(goalDto.getName());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setCompleted(goalDto.getCompleted());
        goal.setUser(mapUserIdToUser(goalDto.getUserId()));
        goal.setPrograms(mapProgramIdsToPrograms(goalDto.getProgramId()));
        goal.setWorkouts(mapWorkoutIdsToWorkouts(goalDto.getWorkoutId()));

        return goal;
    }

    @Override
    public Goal patchGoalDtoToGoal(GoalDto goalDto, Integer id) {
        if (goalDto == null) {
            return null;
        }
        Goal goal = new Goal();

        goal.setId(id);
        goal.setName(goalDto.getName());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setCompleted(goalDto.getCompleted());
        goal.setUser(mapUserIdToUser(goalDto.getUserId()));
        goal.setPrograms(mapProgramIdsToPrograms(goalDto.getProgramId()));
        goal.setWorkouts(mapWorkoutIdsToWorkouts(goalDto.getWorkoutId()));

        return goal;
    }

}
