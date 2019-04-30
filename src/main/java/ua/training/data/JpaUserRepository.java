package ua.training.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.training.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Primary
@Repository
@Transactional
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {

        return entityManager.find(User.class, 1L);
    }

    @Override
    public List<User> findAll(long max, int count) {
        return null;
    }
}
