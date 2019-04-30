package ua.training.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

//@Repository
public class HibernateUserRepository implements UserRepository{

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User save(User user) {
        Session session = getCurentSession();
        Serializable id = session.save(user);
        return new User((Long) id, user.getUsername(), user.getPassword(), user.getEmail(),
                user.getFirstName(),user.getLastName(), user.getRegistrationDate());
    }

    @Override
    public User findByUsername(String username) {
        Session session = getCurentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(criteriaBuilder.equal(root.get("username"), username));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public List<User> findAll(long max, int count) {
        Session session = getCurentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(criteriaBuilder.le(root.get("id"), max));
        Query<User> userQuery = session.createQuery(query);
        userQuery.setMaxResults(count);
        return userQuery.getResultList();
    }
}
