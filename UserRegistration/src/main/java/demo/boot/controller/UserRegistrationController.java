package demo.boot.controller;
import org.springframework.http.HttpEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.model.User;
import demo.boot.service.IUserRegistrationService;
import demo.boot.service.UserRegistrationServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
//@RequestMapping("/client")
public class UserRegistrationController {

	private final IUserRegistrationService userRegistrationService;
	
	@Autowired
    public UserRegistrationController(IUserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	@GetMapping("/")
	@CircuitBreaker(name ="UsersService", fallbackMethod = "getDefaultInfo" )
    public HttpEntity<List<User>> getAllUsers() {
        return userRegistrationService.getAllUsers();
    }
	
    @GetMapping("/{id}")
    public HttpEntity<User> getUserById(@PathVariable Long id) {
        return userRegistrationService.getUserById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userRegistrationService.createUser(user);
    }

    @PutMapping("/{id}")
    @CircuitBreaker(name ="UsersService", fallbackMethod = "getDefaultInfo" )
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userRegistrationService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRegistrationService.deleteUser(id);
    }
    
	public ResponseEntity<String> getDefaultInfo(Exception e)
	{
		return ResponseEntity.ok("Prayer has comeback power...Fallback message");
	}
}
