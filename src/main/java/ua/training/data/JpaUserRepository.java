package ua.training.data;

import org.springframework.stereotype.Repository;
import ua.training.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
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
