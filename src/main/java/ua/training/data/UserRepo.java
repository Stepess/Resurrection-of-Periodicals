package ua.training.data;

import org.springframework.stereotype.Repository;
import ua.training.model.User;

@Repository
public class UserRepo implements UserRepository {
    @Override
    public User save(User user) {
        return new User(13L, "sad", "asd", "asd", "asd", "asd");
    }

    @Override
    public User findByUsername(String username) {
        return new User(13L, "sad", "asd", "asd", "asd", "asd");
    }
}
