package org.skylite.reservationapp.service;

import java.util.Optional;

import org.skylite.reservationapp.dao.AdminDao;
import org.skylite.reservationapp.dto.AdminRequest;
import org.skylite.reservationapp.dto.AdminResponse;
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
	
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest){
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		structure.setMessage("Admin Registered Successfully");
		structure.setData(mapToAdminResponse(adminDao.saveOrUpdateAdmin(mapToAdmin(adminRequest))));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest, int id) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(id);
		if(recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setId(id);
			dbAdmin.setEmail(adminRequest.getEmail());
			dbAdmin.setGst_number(adminRequest.getGst_number());
			dbAdmin.setName(adminRequest.getName());
			dbAdmin.setPassword(adminRequest.getPassword());
			dbAdmin.setPhone(adminRequest.getPhone());
			dbAdmin.setTravels_name(adminRequest.getTravels_name());
			structure.setData(mapToAdminResponse(adminDao.saveOrUpdateAdmin(dbAdmin)));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AdminNotFoundException("Cannot update as Admin id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int id) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(id);
		if(recAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(recAdmin.get()));
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(String email) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(email);
		if(recAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(recAdmin.get()));
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(id);
		if(recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			structure.setData("Admin Deleted");
			adminDao.deleteAdmin(dbAdmin);
			structure.setMessage("Admin with id"+dbAdmin.getId()+" is deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		
		throw new AdminNotFoundException("Admin does not exists");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(long phone, String  password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verifyByPhoneAndPassword(phone, password);
		if(recAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(recAdmin.get()));
			structure.setMessage("Admin Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("No Admin exists with given phone and password");
	}
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(String email, String  password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verifyByEmailAndPassword(email, password);
		if(recAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(recAdmin.get()));
			structure.setMessage("Admin Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("No Admin exists with given email and password");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> resetPassword(int admin_id, String password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(admin_id);
		if(recAdmin.isPresent()) {
			Admin admin = recAdmin.get();
			admin.setPassword(password);
			adminDao.saveOrUpdateAdmin(admin);
			structure.setData(mapToAdminResponse(admin));
			structure.setMessage("Password has been reset");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Admin doesn't exists.");
		
	}
	
	private Admin mapToAdmin(AdminRequest admin) {
		return Admin.builder().name(admin.getName()).phone(admin.getPhone()).gst_number(admin.getGst_number())
				.email(admin.getEmail()).travels_name(admin.getTravels_name())
				.password(admin.getPassword()).build();
	}
	
	private AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder().id(admin.getId()).name(admin.getName()).phone(admin.getPhone())
				.email(admin.getEmail()).gst_number(admin.getGst_number()).password(admin.getPassword())
				.travels_name(admin.getTravels_name()).build();
	}
	
	
}
