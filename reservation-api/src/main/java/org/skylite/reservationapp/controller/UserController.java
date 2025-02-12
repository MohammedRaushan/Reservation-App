package org.skylite.reservationapp.controller;

import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.dto.UserRequest;
import org.skylite.reservationapp.dto.UserResponse;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid @RequestBody UserRequest userRequest) {
		return service.saveUser(userRequest);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable int id) {
		return service.updateUser(userRequest, id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(@PathVariable int id) {
		return service.getUser(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(@RequestParam long phone,@RequestParam String password) {
		return service.verifyUser(phone, password);
	}
	
	@GetMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(@RequestParam String email,@RequestParam String password) {
		return service.verifyUser(email, password);
	}
}













