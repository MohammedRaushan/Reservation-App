package org.skylite.reservationapp.dao;

import java.util.Optional;

import org.skylite.reservationapp.model.Admin;
import org.skylite.reservationapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveOrUpdateAdmin(Admin admin){
		return adminRepository.save(admin);
	}
	
	public Optional<Admin> getAdmin(int id) {
		return adminRepository.findById(id);
	}
	
	public void deleteAdmin(Admin admin) {
		adminRepository.delete(admin);
	}
	
	public Optional<Admin> verifyByPhoneAndPassword(long phone, String password){
		return adminRepository.findByPhoneAndPassword(phone, password);
	}
	
	public Optional<Admin> verifyByEmailAndPassword(String email, String password){
		return adminRepository.findByEmailAndPassword(email, password);
	}
	
	public Optional<Admin> getAdmin(String email){
		return adminRepository.findByEmail(email);
	}
}
