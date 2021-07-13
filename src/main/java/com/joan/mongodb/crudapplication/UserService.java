package com.joan.mongodb.crudapplication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired 
	private UserRepository repository;
	
	public Optional<User> findOne(String id) {
		return repository.findById(id);
	}
	public List<User> findMany() {
		return repository.findAll();
	}
	public User saveUser(User user) {
		return repository.save(user);
	}
	public List<User> saveListUser(List<User> users) {
		return repository.saveAll(users);
	}
	public void deleteAllUsers() {
		repository.deleteAll();
	}
	
	public void deleteUser(String id) {
		repository.deleteById(id);
	}
	public User updateUser(String id,User user) throws UserException{
		Optional<User> userFound = repository.findById(id);
		if(!userFound.isPresent()) {
			throw new UserException("404", "Can not find a user for updating with id = " + id);
		}
		User _user = userFound.get();
		_user.setName(user.getName());
		_user.setEmail(user.getEmail());
		_user.setType(user.getType());
		_user.setPassword(user.getPassword());
		repository.save(_user);
		return _user;
	}
}
