package org.skylite.reservationapp.service;

import java.util.Optional;

import org.skylite.reservationapp.dao.AdminDao;
import org.skylite.reservationapp.dto.AdminRequest;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.exception.AdminNotFoundException;
import org.skylite.reservationapp.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminRequest adminRequest){
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setMessage("Admin Registered Successfully");
		structure.setData(adminDao.saveOrUpdateAdmin(mapToAdmin(adminRequest)));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(AdminRequest adminRequest, int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(id);
		if(recAdmin.isPresent()) {
			Admin dbAdmin = mapToAdmin(adminRequest);
			dbAdmin.setId(id);
			structure.setData(adminDao.saveOrUpdateAdmin(dbAdmin));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AdminNotFoundException("Cannot update as Admin id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(id);
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(id);
		if(recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			structure.setData(dbAdmin);
			adminDao.deleteAdmin(dbAdmin);
			structure.setMessage("Admin deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		
		throw new AdminNotFoundException("Admin does not exists");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(long phone, String  password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verifyByPhoneAndPassword(phone, password);
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Verified");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		throw new AdminNotFoundException("No Admin exists with given phone and password");
	}
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(String email, String  password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verifyByEmailAndPassword(email, password);
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Verified");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		throw new AdminNotFoundException("No Admin exists with given email and password");
	}
	
	private Admin mapToAdmin(AdminRequest admin) {
		return Admin.builder().name(admin.getName()).phone(admin.getPhone()).gst_number(admin.getGst_number())
				.email(admin.getEmail()).travels_name(admin.getTravels_name())
				.password(admin.getPassword()).build();
	}
	
	
}
