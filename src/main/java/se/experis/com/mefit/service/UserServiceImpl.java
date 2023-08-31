package se.experis.com.mefit.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.experis.com.mefit.model.Goal;
import se.experis.com.mefit.model.User;
import se.experis.com.mefit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
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
    public User update(Integer id, User user) {
        return userRepository.save(user);

    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addGoal(Goal goal, Integer id) {
        User user = userRepository.findById(id).get();

        user.setCurrentGoal(goal);
        userRepository.save(user);
        return user;
    }

}
