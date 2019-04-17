package ua.training.data;

import ua.training.model.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

}
