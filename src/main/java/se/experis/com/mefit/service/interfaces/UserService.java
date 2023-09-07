package se.experis.com.mefit.service.interfaces;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.service.CrudService;

public interface UserService extends CrudService<User, String> {
    User addGoal(Goal goal, String id);
}
