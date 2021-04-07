package com.meetpaija.quizeeserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetpaija.quizeeserver.common.ResponseUtility;
import com.meetpaija.quizeeserver.document.UserDocument;
import com.meetpaija.quizeeserver.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<Object> getUsers() {

		try {

			List<UserDocument> users = userService.getUserList();
			return ResponseUtility.getInstance().successResponse(users);

		} catch (Exception e) {

			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> doLogin(@RequestBody @Valid String credentials ) {
		
		try {
			UserDocument user=userService.doLogin(credentials);
			return ResponseUtility.getInstance().successResponse(user);
		}
		catch(Exception e) {
			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getUserByID(@PathVariable("id") String userID) {

		try {

			UserDocument user = userService.getUserByID(userID);
			return ResponseUtility.getInstance().successResponse(user);

		} catch (Exception e) {

			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserDocument user) {

		try {

			UserDocument createdUser = userService.addUser(user);
			return ResponseUtility.getInstance().successResponse(createdUser);

		} catch (Exception e) {

			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> updateUser(@RequestBody @Valid UserDocument user,
			@PathVariable("id") String userId) {

		try {

			UserDocument updatedUser = userService.updateUser(user, userId);
			return ResponseUtility.getInstance().successResponse(updatedUser);

		} catch (Exception e) {

			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String userId) {

		try {

			userService.deleteUser(userId);
			return ResponseUtility.getInstance().setEmptyResponse(null);

		} catch (Exception e) {

			return ResponseUtility.getInstance().failureResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
