package com.meetpaija.quizeeserver.service;

import java.util.List;

import com.meetpaija.quizeeserver.document.UserDocument;

public interface UserService {

	UserDocument addUser(UserDocument user) throws Exception;

	List<UserDocument> getUserList();

	UserDocument findByName(String name);

	UserDocument updateUser(UserDocument user, String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

	UserDocument getUserByID(String userID) throws Exception;

	UserDocument doLogin(String credentials) throws Exception;
}
