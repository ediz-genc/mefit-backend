package se.experis.com.mefit.service.implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.repository.UserRepository;
import se.experis.com.mefit.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        try {
            return userRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Set<User> findAll() {
        return Set.copyOf(userRepository.findAll());
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        return userRepository.save(user);

    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addGoal(Goal goal, String id) {
        User user = userRepository.findById(id).get();

        user.setCurrentGoal(goal);
        userRepository.save(user);
        return user;
    }

}
