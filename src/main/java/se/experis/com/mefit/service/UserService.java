package se.experis.com.mefit.service;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;

public interface UserService extends CrudService<User, Integer> {
    User addGoal(Goal goal, Integer id);
}
