package se.experis.com.mefit.mapper.implementations;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.experis.com.mefit.mapper.abstracts.UserMapper;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.model.DTOs.UserDtos.PutUserDto;
import se.experis.com.mefit.model.DTOs.UserDtos.UserDto;

@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setBio(user.getBio());
        userDto.setWeight(user.getWeight());
        userDto.setLength(user.getLength());
        if (user.getCurrentGoal() != null) {
            userDto.setCurrentGoalId(user.getCurrentGoal().getId());
        } else {
            userDto.setCurrentGoalId(null);
        }
        userDto.setProfilePicUrl(user.getProfilePicUrl());
        userDto.setGoalHistoryId(user.getGoalHistory().stream().map(s -> s.getId()).collect(Collectors.toSet()));

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User(userDto.getId());

        
        user.setBio(userDto.getBio());
        user.setUsername(userDto.getUsername());
        user.setProfilePicUrl(userDto.getProfilePicUrl());
        user.setLength(userDto.getLength());
        user.setWeight(userDto.getWeight());
        if(userDto.getCurrentGoalId() != 0) {
            user.setCurrentGoal(mapGoalIdToGoal(userDto.getCurrentGoalId()));
        }
        user.setGoalHistory(mapGoalIdsToGoals(userDto.getGoalHistoryId()));

        return user;
    }

    @Override
    public User putUserDtoToUser(String id, PutUserDto putUserDto) {
        if (putUserDto == null) {
            return null;
        }
        User user = new User(id);

        
        user.setBio(putUserDto.getBio());
        user.setUsername(putUserDto.getUsername());
        user.setProfilePicUrl(putUserDto.getProfilePicUrl());
        user.setLength(putUserDto.getLength());
        user.setWeight(putUserDto.getWeight());

        return user;
    }

}
