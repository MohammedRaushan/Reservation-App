package org.skylite.reservationapp.repository;

import java.util.Optional;

import org.skylite.reservationapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	Optional<Admin> findByPhoneAndPassword(long phone, String password);
	
	Optional<Admin> findByEmailAndPassword(String email, String password);
	
	Optional<Admin> findByEmail(String email);
}
