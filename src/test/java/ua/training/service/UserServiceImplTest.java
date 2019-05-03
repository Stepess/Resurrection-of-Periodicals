package ua.training.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.data.UserRepository;
import ua.training.exception.UserNotFoundException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldThrowExceptionWhenSearchUsersWithNegativeId() {
        long id = -13;
        int offset = 0;
        int limit = 3;

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Max id cannot be negative");

        userService.findUsers(id, offset, limit);
    }

    @Test
    public void shouldThrowExceptionWhenSearchUsersWithOffesetBiggerThanLimit() {
        long id = 3;
        int offset = 42;
        int limit = 1;

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Offset should be less than limit");

        userService.findUsers(id, offset, limit);
    }

    @Test
    public void shouldThrowExceptionWhenUserByUsernameNotFound() {
        String username = "UnexistingUsername";

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        expectedException.expect(UserNotFoundException.class);

        userService.findByUsername(username);
    }

}