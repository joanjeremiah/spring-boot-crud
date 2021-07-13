package com.joan.mongodb.crudapplication;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	@PostMapping("/create-list")
	public List<User> createListUser(@RequestBody List<User> users) {
		return service.saveListUser(users);
	}
	@GetMapping("/find/all")
	public List<User> findAllUser() {
		return service.findMany();
	}
	@GetMapping("/find/{id}")
	public Optional<User> findUser(@PathVariable String id) {
		return service.findOne(id);
	}
	
	@GetMapping("/delete/all")
	public void deleteAllUser() {
		service.deleteAllUsers();
	}
	@GetMapping("/delete/{id}")
	public void deleteUser(@PathVariable String id) {
		service.deleteUser(id);
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateUser(@PathVariable String id,@RequestBody User user,HttpServletRequest req) throws UserException {
		try {
			service.updateUser(id, user);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updated User successfully","Updated",req.getRequestURI()),HttpStatus.OK);
		}
		catch(UserException ex) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Cannot find user with an id",ex.getMessage(), req.getRequestURI()),HttpStatus.NOT_FOUND);
		}
	}
}
