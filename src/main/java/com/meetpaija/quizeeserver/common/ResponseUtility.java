package com.meetpaija.quizeeserver.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtility {

	private final Logger log = LoggerFactory.getLogger(ResponseUtility.class);
	private static ResponseUtility responseEntity;

	public static ResponseUtility getInstance() {
		if (responseEntity == null) {
			responseEntity = new ResponseUtility();
		}
		return responseEntity;
	}

	public ResponseEntity<Object> successResponse(Object data) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(data);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		apiResponse.setTimestamp(new Date());
		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
	}

	public ResponseEntity<Object> failureResponse(Exception ex, HttpStatus status, String message) {

		log.error("Unable to perform action : ", ex);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setTimestamp(new Date());
		apiResponse.setError(true);
		apiResponse.setStatus(status);
		apiResponse.setMessage(message);
		apiResponse.setDebugMessage(ex.toString());
		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
	}

	public ResponseEntity<Object> setEmptyResponse(Object object) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setTimestamp(new Date());
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
	}

}
