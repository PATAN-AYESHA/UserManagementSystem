package demo.boot.service;

import java.util.List;

import org.apache.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import demo.boot.model.User;

public interface IUserRegistrationService {

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> updateUser(Long id, User user);

    ResponseEntity<Void> deleteUser(Long id);
}
