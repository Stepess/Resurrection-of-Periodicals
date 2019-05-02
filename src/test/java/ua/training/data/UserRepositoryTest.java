package ua.training.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.training.config.PersistenceConfig;
import ua.training.config.RootConfig;
import ua.training.model.User;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, RootConfig.class})
@ActiveProfiles("test")
@Transactional
public class UserRepositoryTest {

    //@Resource
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSave() {

        // GIVEN

        User user =
                new User("testuser1", "secret", "user@ema.il", "Test", "Testing");
        User saved = userRepository.save(user);

        // WHEN

        User byId = userRepository.findById(saved.getId());

        // THEN

        assertEquals(user, byId);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDelete() {

        // GIVEN

        User user =
                new User("userToDelete", "notneccesary", "iWillBeDeleted@doman.com", "Delete", "Backspace");
        User saved = userRepository.save(user);

        // WHEN

        userRepository.delete(saved);
        userRepository.findByUsername(user.getUsername());
    }

    @Test
    public void shouldFindById() {

        // GIVEN

        User user =
                new User("findMeById", "pass", "find@email.com", "Find", "Finded");
        userRepository.save(user);

        // WHEN

        User byId = userRepository.findById(user.getId());

        // THEN

        assertNotNull(byId);
        assertEquals(user, byId);
    }

    @Test
    public void shouldFindByUsername() {

        // GIVEN

        User user =
                new User("findMeByUsername", "pass", "find@email.com", "Find", "Finded");
        userRepository.save(user);

        // WHEN

        User byUsername = userRepository.findByUsername(user.getUsername());

        // THEN

        assertNotNull(byUsername);
        assertEquals(user, byUsername);
    }

    @Test
    public void shouldUpdate() {

        // GIVEN

        String oldName = "OldName";
        String weakpass = "weakpass";
        String email = "email@email.com";
        String name = "Name";
        String surname = "Surname";

        User oldUser =
                new User(oldName, weakpass, email, name, surname);
        User saved = userRepository.save(oldUser);

        // WHEN

        User newUser =
                new User(saved.getId(), "newName", "strongpass",  "Name", "Surname", "email@email.com", saved.getRegistrationDate());
        userRepository.update(newUser);

        // THEN

        User byId = userRepository.findById(saved.getId());

        // to check updated fields
        assertNotEquals(oldName, byId.getUsername());
        assertNotEquals(weakpass, byId.getPassword());

        // to check not updated fields
        assertEquals(email, byId.getEmail());
        assertEquals(name, byId.getFirstName());
        assertEquals(surname, byId.getLastName());
    }

}