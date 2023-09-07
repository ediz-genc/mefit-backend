package se.experis.com.mefit.mapper.implementations;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.mapper.abstracts.GoalMapper;
import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.DTOs.GoalDtos.GoalDto;
import se.experis.com.mefit.model.DTOs.GoalDtos.PutGoalDto;

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
        goalDto.setCompletedWorkoutId(
                goal.getCompletedPrograms().stream().map(s -> s.getId()).collect(Collectors.toSet()));
        goalDto.setCompletedWorkoutId(
                goal.getCompletedWorkouts().stream().map(s -> s.getId()).collect(Collectors.toSet()));
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
        goal.setCompletedWorkouts(mapWorkoutIdsToWorkouts(goalDto.getCompletedWorkoutId()));
        goal.setCompletedPrograms(mapProgramIdsToPrograms(goalDto.getCompletedProgramId()));

        return goal;
    }

    @Override
    public Goal putGoalDtoToGoal(PutGoalDto putGoalDto, Integer id) {
        if (putGoalDto == null) {
            return null;
        }
        Goal goal = new Goal();

        goal.setId(id);
        goal.setName(putGoalDto.getName());
        goal.setStartDate(putGoalDto.getStartDate());
        goal.setEndDate(putGoalDto.getEndDate());
        goal.setCompleted(putGoalDto.getCompleted());

        return goal;
    }

}
