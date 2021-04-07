package com.meetpaija.quizeeserver.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.meetpaija.quizeeserver.common.TimeStampModel;

@Document(collection = "users")
@JsonInclude(value=Include.NON_NULL)
public class UserDocument extends TimeStampModel implements Serializable {

	private static final long serialVersionUID = 7811192426661602551L;

	@Id
	private String id;
	private String username;
	private String type;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
