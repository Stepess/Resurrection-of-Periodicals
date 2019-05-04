package ua.training.service;

import static com.google.common.base.Preconditions.checkArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.training.data.UserRepository;
import ua.training.exception.UserNotFoundException;
import ua.training.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUsers(long max, int offset, int limit) {
        checkArgument(max < 0, "Max id cannot be negative");
        checkArgument(offset > limit, "Offset should be less than limit");

        List<User> users = userRepository.findByIdLessThanEqual(max, PageRequest.of(offset, limit));

        if (CollectionUtil.isNullOrEmpty(users)) {
            throw new UserNotFoundException();
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
