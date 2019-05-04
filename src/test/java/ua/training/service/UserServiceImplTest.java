package ua.training.service;

import com.google.common.collect.Lists;
import org.hibernate.criterion.Example;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import ua.training.data.UserRepository;
import ua.training.exception.UserNotFoundException;
import ua.training.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
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
    public void shouldSearchUsers() {
        long id = 3;
        int offset = 1;
        int limit = 13;
        List<User> expected = getUsers();
        PageRequest request = PageRequest.of(offset, limit);

        when(userRepository.findByIdLessThanEqual(id, request))
                .thenReturn(expected);

        List<User> actual = userService.findUsers(id, offset, limit);

        verify(userRepository).findByIdLessThanEqual(id, request);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchUserById() {
        long id = 13;
        User expected = getUser();

        when(userRepository.findById(id))
                .thenReturn(Optional.of(expected));

        User actual = userService.findById(id);

        verify(userRepository).findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchUserByUsername() {
        String username = "nickname";
        User expected = getUser();

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(expected));

        User actual = userService.findByUsername(username);

        verify(userRepository).findByUsername(username);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveUser() {
        User expected = getUser();

        when(userRepository.save(expected))
                .thenReturn(expected);

        User actual = userService.save(expected);

        verify(userRepository).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteUser() {
        User userToDelete = getUser();

        userService.delete(userToDelete);

        verify(userRepository).delete(userToDelete);
    }

    @Test
    public void shouldThrowExceptionWhenGetEmptyCollectionFromDB() {
        long id = 3;
        int offset = 1;
        int limit = 13;

        when(userRepository.findByIdLessThanEqual(anyLong(), any()))
                .thenReturn(Collections.EMPTY_LIST);
        expectedException.expect(UserNotFoundException.class);

        userService.findUsers(id, offset, limit);
    }

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

    @Test
    public void shouldThrowExceptionWhenUserByIdNotFound() {
        long id = 13;

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        expectedException.expect(UserNotFoundException.class);

        userService.findById(id);
    }

    private List<User> getUsers() {
        return Lists.newArrayList(
                new User("user", "passMe", "u@ema.il", "firstName", "lastName"),
                new User("skif", "123qwe", "ema@il.com", "Skif", "Sarmat"),
                new User("senapu", "qazwsx13", "japan@ema.il", "anime12", "shaman")
        );
    }

    private User getUser() {
        return new User("user", "passMe", "u@ema.il", "firstName", "lastName");
    }

}