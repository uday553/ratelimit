package com.fma.ratelimit.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Response {

	private String validationFailed="Bad Request";
	private String success="Successful";
	private String problemInProcessing="problem in processing request";
	
	public ResponseEntity validationFailedResponse = new ResponseEntity<String>(validationFailed, HttpStatus.BAD_REQUEST);
	
	public ResponseEntity successResponse = new ResponseEntity<String>(success, HttpStatus.OK);
	
	public ResponseEntity createdResponse = new ResponseEntity<String>(success, HttpStatus.CREATED);
	
	public ResponseEntity ErrorResponse = new ResponseEntity<String>(problemInProcessing, HttpStatus.BAD_REQUEST);

	public ResponseEntity getSuccessResponse(String message)
	{
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
