package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import demo.boot.model.User;

@Service
public class UserRegistrationServiceImpl implements IUserRegistrationService {

	private final RestTemplate restTemplate;
	
	@Autowired
	public UserRegistrationServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
    private String getBaseUrl() {
//        return "http://localhost:8080"; // Change this URL based on your service endpoint
       return  "http://UsersService";
    }
	
	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, null, responseType);
        return responseEntity;
	}

	@Override
	public ResponseEntity<User> getUserById(Long id) {
		return restTemplate.getForEntity(getBaseUrl() + "/" + id, User.class);
	}

	@Override
	public ResponseEntity<User> createUser(User user) {
		return restTemplate.postForEntity(getBaseUrl(), user, User.class);
	}

	@Override
	public ResponseEntity<User> updateUser(Long id, User user) {
		return restTemplate.exchange(getBaseUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(user), User.class);
    }

	@Override
	public ResponseEntity<Void> deleteUser(Long id) {
        restTemplate.delete(getBaseUrl() + "/" + id);
        return ResponseEntity.ok().build();
	}

	public ResponseEntity<String> getDefaultInfo(Exception e)
	{
		return ResponseEntity.ok("Prayer has comeback power...Fallback message");
	}
}
