package ua.training.data;

import org.springframework.stereotype.Repository;
import ua.training.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public User save(User user) {
        entityManagerFactory.createEntityManager().persist(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {

        return entityManagerFactory.createEntityManager().find(User.class, 1);
    }

    @Override
    public List<User> findAll(long max, int count) {
        return null;
    }
}
