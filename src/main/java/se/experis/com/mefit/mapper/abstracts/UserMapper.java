package se.experis.com.mefit.mapper.abstracts;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.DTOs.UserDtos.PutUserDto;
import se.experis.com.mefit.model.DTOs.UserDtos.UserDto;
import se.experis.com.mefit.service.interfaces.GoalService;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected GoalService goalService;

    @Mapping(target = "goal", source = "goal", qualifiedByName = "goalIdsToGoal")

    public abstract UserDto userToUserDto(User user);

    @Named("goalIdsToGoal")
    public Set<Goal> mapGoalIdsToGoals(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> goalService.findById(s)).collect(Collectors.toSet());
    }

    @Named("goalIdToGoal")
    public Goal mapGoalIdToGoal(Integer id) {
        if (id == null) {
            return null;
        }
        return goalService.findById(id);
    }

    public abstract User userDtoToUser(UserDto userDto);

    public abstract User putUserDtoToUser(Integer id, PutUserDto putUserDto);
}
