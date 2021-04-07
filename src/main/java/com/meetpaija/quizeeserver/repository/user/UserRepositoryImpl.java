package com.meetpaija.quizeeserver.repository.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import com.meetpaija.quizeeserver.document.UserDocument;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<UserDocument> findByName(String username) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return mongoTemplate.find(query, UserDocument.class);
	}

	@Override
	public List<UserDocument> doLogin(String usernameOrEmail, String password) {
		
		Query query = new Query();
		Criteria userNameOrEmailCriteria = new Criteria();
		userNameOrEmailCriteria.orOperator(Criteria.where("username").is(usernameOrEmail),Criteria.where("email").is(usernameOrEmail));
		query.addCriteria(userNameOrEmailCriteria);
		query.addCriteria(Criteria.where("password").is(password));
		return mongoTemplate.find(query, UserDocument.class);
	}

}
