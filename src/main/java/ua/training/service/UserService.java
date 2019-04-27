package ua.training.service;

import ua.training.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers(long max, int count);

    User findByUsername(String username);

    User save(User user);
}
