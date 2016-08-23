package com.betr.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ResourceHandler {

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<String> exceptionHandler(Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
