package ua.training.data;

import ua.training.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();

}
