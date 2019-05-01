package ua.training.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.training.model.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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
    public User update(User user) {
        User userDB = findById(user.getId());
        userDB.setUsername(user.getUsername());
        userDB.setPassword(user.getPassword());
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setEmail(user.getEmail());
        return user;
    }

    @Override
    public User delete(User user) {
        entityManager.remove(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        ParameterExpression<String> parameter = cb.parameter(String.class, "username");
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        criteriaQuery.select(from).where(cb.equal(from.get("username"), parameter));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(parameter, username);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll(long max, int count) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        ParameterExpression<Long> id = cb.parameter(Long.class);
        criteriaQuery.select(from).where(cb.le(from.get("id"), id));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.setParameter(id, max)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
