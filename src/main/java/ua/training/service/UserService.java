package ua.training.service;

import ua.training.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers(long max, int offset, int limit);

    User findByUsername(String username);

    User save(User user);

    User findById(Long id);
}
