package com.meetpaija.quizeeserver.service.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetpaija.quizeeserver.document.UserDocument;
import com.meetpaija.quizeeserver.repository.UserRepository;
import com.meetpaija.quizeeserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDocument addUser(UserDocument user) throws Exception {
		log.info("Saving user to DB");

		UserDocument existingUser = findByName(user.getUsername());

		if (existingUser != null) {
			throw new Exception("User already added.." + user.getUsername());
		}

		return userRepository.save(user);
	}

	@Override
	public List<UserDocument> getUserList() {

		log.info("Fetching all users");
		return (List<UserDocument>) userRepository.findAll();
	}

	@Override
	public UserDocument findByName(String name) {

		List<UserDocument> user = userRepository.findByName(name);

		if (user != null && !user.isEmpty() && user.size()==1) {
			return user.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UserDocument updateUser(UserDocument user, String userID) throws Exception {

		log.info("Updating user to DB");

		Optional<UserDocument> existingUser = userRepository.findById(userID);

		if (!existingUser.isPresent()) {

			throw new Exception("User not found for ID : " + userID);
		}

		return userRepository.save(user);
	}

	@Override
	public void deleteUser(String userID) throws Exception {

		log.info("Removing user from DB");

		Optional<UserDocument> existingUser = userRepository.findById(userID);

		if (!existingUser.isPresent()) {

			throw new Exception("User not found for ID : " + userID);
		}

		userRepository.deleteById(userID);
	}

	@Override
	public UserDocument getUserByID(String userID) throws Exception {

		Optional<UserDocument> user = userRepository.findById(userID);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new Exception("User not found for ID : " + userID);
		}
	}

	@Override
	public UserDocument doLogin(String credentials) throws Exception {
		
		JSONObject obj=new JSONObject(credentials);
		String usernameOrEmail=obj.getString("usernameOrEmail").trim();
		String password=obj.getString("password").trim();
		
		if(usernameOrEmail == null || password == null || usernameOrEmail.isEmpty() || password.isEmpty()) {
			throw new Exception("Unable to login!! Please check your credetials!!");
		}
		
		List<UserDocument> user = userRepository.doLogin(usernameOrEmail, password);
		
		if (user != null && !user.isEmpty() && user.size()==1) {
			return user.get(0);
		} else {
			throw new Exception("Unable to login!! User not found!!");
		}
	}

}
