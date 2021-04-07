package com.meetpaija.quizeeserver.repository.user;

import java.util.List;

import com.meetpaija.quizeeserver.document.UserDocument;

public interface UserRepositoryCustom {

	List<UserDocument> findByName(String username);

	List<UserDocument> doLogin(String usernameOrEmail, String password);
}
