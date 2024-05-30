package org.skylite.reservationapp.controller;

import org.skylite.reservationapp.dto.AdminRequest;
import org.skylite.reservationapp.dto.AdminResponse;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@Valid @RequestBody AdminRequest adminRequest){
		return service.saveAdmin(adminRequest);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid @RequestBody AdminRequest adminRequest, @PathVariable int id) {
		return service.updateAdmin(adminRequest, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable int id) {
		return service.findAdmin(id);
	}
	
	@GetMapping("/find-by-email")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@RequestParam String email) {
		return service.findAdmin(email);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable int id) {
		return service.deleteAdmin(id);
	}
	
	@GetMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(@RequestParam long phone,@RequestParam String  password) {
		return service.verifyAdmin(phone, password);
	}
	
	@GetMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(@RequestParam String email,@RequestParam String  password) {
		return service.verifyAdmin(email, password);
	}
	@PutMapping("/reset-password/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> resetPassword(@PathVariable(name="id") int admin_id, @RequestParam String password) {
		return service.resetPassword(admin_id, password);
	}
}
