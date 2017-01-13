package com.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.User;
import com.repositories.UserRepository;
import com.services.UserService;

@Service
@Transactional
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) throws Exception {
		user.setCreatedDateTime(LocalDateTime.now());
		user.setUpdatedDateTime(LocalDateTime.now());
		return userRepository.save(user);
	}

	public User updateUser(User user) throws Exception {
		user.setUpdatedDateTime(LocalDateTime.now());
		return userRepository.save(user);
	}

	public List<User> getAllUsers() throws Exception {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) throws Exception {
		return userRepository.findOne(userId);
	}

	public void deleteUser(Long userId) throws Exception {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new Exception("User with " + userId + " not found");
		}
		userRepository.delete(userId);
	}

}
