package com.betr.server.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.betr.server.domain.User;
import com.betr.server.domain.UserRole;
import com.betr.server.repo.UserRepository;

@Controller
public class UserSignupResource {

	@RequestMapping(value = "/api/create/user", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<String> createUser(@RequestBody User user) {
		UserRepository repo = new UserRepository();
		if(repo.getUserByEmail(user.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this email already exists!");
		}
		//Override to only create users
		user.setRole(UserRole.USER);
		repo.createUser(user);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
