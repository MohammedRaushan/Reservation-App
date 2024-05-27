package org.skylite.reservationapp.repository;

import java.util.Optional;

import org.skylite.reservationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByPhoneAndPassword(long phone, String password);

	Optional<User> findByEmailAndPassword(String email, String password);
}
