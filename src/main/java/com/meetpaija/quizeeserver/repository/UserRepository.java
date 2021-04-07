package com.meetpaija.quizeeserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meetpaija.quizeeserver.document.UserDocument;
import com.meetpaija.quizeeserver.repository.user.UserRepositoryCustom;

@Repository
public interface UserRepository extends CrudRepository<UserDocument, String>, UserRepositoryCustom {

}
