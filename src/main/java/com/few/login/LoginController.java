package com.few.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Value("${FEWService.baseURL}")
	private String baseURL;
	
	@GetMapping("/validate")
	public ResponseEntity<String> getSessionValidity() {
		return new ResponseEntity<String>("Session is Valid", HttpStatus.OK);
	}
	@GetMapping("/baseurl")
	public ResponseEntity<String> getBaseURL() {
		return new ResponseEntity<String>(baseURL, HttpStatus.OK);
	}
	
}
