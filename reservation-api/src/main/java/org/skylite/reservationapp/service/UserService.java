package org.skylite.reservationapp.service;

import java.util.Optional;

import org.skylite.reservationapp.dao.UserDao;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.dto.UserRequest;
import org.skylite.reservationapp.dto.UserResponse;
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
	
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		structure.setData(mapToUserResponse(userDao.saveOrUpdateUser(mapToUser(userRequest))));
		structure.setMessage("User Registered Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest, int id) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			User dbUser = mapToUser(userRequest);
			dbUser.setId(id);
			structure.setData(mapToUserResponse(userDao.saveOrUpdateUser(dbUser)));
			structure.setMessage("User updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("Cannot update as user id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(int id) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			structure.setData(mapToUserResponse(recUser.get()));
			structure.setMessage("User found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.getUser(id);
		if(recUser.isPresent()) {
			User user = recUser.get();
			userDao.deleteUser(user);
			structure.setData("User Deleted");
			structure.setMessage("User with id "+user.getId() +" Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(long phone, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyByPhoneAndPassword(phone, password);
		if(recUser.isPresent()) {
			structure.setData(mapToUserResponse(recUser.get()));
			structure.setMessage("User Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(String email, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verifyByEmailAndPassword(email, password);
		if(recUser.isPresent()) {
			structure.setData(mapToUserResponse(recUser.get()));
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
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().age(user.getAge())
				.email(user.getEmail())
				.gender(user.getGender())
				.name(user.getName())
				.password(user.getPassword())
				.phone(user.getPhone())
				.id(user.getId()).build();
	}
}














