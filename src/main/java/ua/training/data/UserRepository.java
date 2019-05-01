package ua.training.data;

import ua.training.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll(long max, int count);

    User findById(Long id);

    User findByUsername(String username);

    User save(User user);

    User update(User user);

    User delete(User user);

}
