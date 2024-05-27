package org.skylite.reservationapp.controller;

import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.dto.UserRequest;
import org.skylite.reservationapp.model.User;
import org.skylite.reservationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody UserRequest userRequest) {
		return service.saveUser(userRequest);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody UserRequest userRequest, @PathVariable int id) {
		return service.updateUser(userRequest, id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> getUser(@PathVariable int id) {
		return service.getUser(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam long phone,@RequestParam String password) {
		return service.verifyUser(phone, password);
	}
	
	@GetMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam String email,@RequestParam String password) {
		return service.verifyUser(email, password);
	}
}













