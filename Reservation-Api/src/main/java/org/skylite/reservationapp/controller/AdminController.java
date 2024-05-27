package org.skylite.reservationapp.controller;

import org.skylite.reservationapp.dto.AdminRequest;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.model.Admin;
import org.skylite.reservationapp.service.AdminService;
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
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody AdminRequest adminRequest){
		return service.saveAdmin(adminRequest);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody AdminRequest adminRequest, @PathVariable int id) {
		return service.updateAdmin(adminRequest, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findAdmin(@PathVariable int id) {
		return service.findAdmin(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@PathVariable int id) {
		return service.deleteAdmin(id);
	}
	
	@GetMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(@RequestParam long phone,@RequestParam String  password) {
		return service.verifyAdmin(phone, password);
	}
	
	@GetMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(@RequestParam String email,@RequestParam String  password) {
		return service.verifyAdmin(email, password);
	}
}
