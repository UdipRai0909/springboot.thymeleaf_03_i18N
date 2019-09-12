package springboot.thymeleaf_01_basics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import springboot.thymeleaf_01_basics.entities.User;

@Service
public class UserService {
	
	private List<User> userList = new ArrayList<>();
	
	public void addUser(User user) {
		userList.add(user);
	}

	public Object getAllUsers() {
		return userList;
	}
}
