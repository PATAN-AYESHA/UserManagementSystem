package demo.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.boot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT t FROM User t", User.class).getResultList();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public void deleteUser(User user) {
        entityManager.remove(user);
    }
}
