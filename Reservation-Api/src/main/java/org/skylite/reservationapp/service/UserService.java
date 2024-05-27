package org.skylite.reservationapp.service;

import java.util.Optional;

import org.skylite.reservationapp.dao.UserDao;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.dto.UserRequest;
import org.skylite.reservationapp.exception.UserNotFoundException;
import org.skylite.reservationapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(UserRequest userRequest) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userDao.saveOrUpdateUser(mapToUser(userRequest)));
		structure.setMessage("User Registered Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(UserRequest userRequest, int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			User dbUser = mapToUser(userRequest);
			dbUser.setId(id);
			structure.setData(userDao.saveOrUpdateUser(dbUser));
			structure.setMessage("User updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("Cannot update as user id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<User>> getUser(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			User user = recUser.get();
			userDao.deleteUser(user);
			structure.setData(user);
			structure.setMessage("User Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyByPhoneAndPassword(phone, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyByEmailAndPassword(email, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	private User mapToUser(UserRequest userRequest) {
		return User.builder().name(userRequest.getName())
				.age(userRequest.getAge())
				.email(userRequest.getEmail())
				.gender(userRequest.getGender())
				.phone(userRequest.getPhone())
				.password(userRequest.getPassword()).build();
	}
}














