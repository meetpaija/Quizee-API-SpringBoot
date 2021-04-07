package com.meetpaija.quizeeserver.common;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class TimeStampModel {

	@LastModifiedDate
	private Date _updatedAt;

	@CreatedDate
	private Date _createdAt;

	public Date get_updatedAt() {
		return _updatedAt;
	}
	public void set_updatedAt(Date _updatedAt) {
		this._updatedAt = _updatedAt;
	}
	public Date get_createdAt() {
		return _createdAt;
	}
	public void set_createdAt(Date _createdAt) {
		this._createdAt = _createdAt;
	}
}
