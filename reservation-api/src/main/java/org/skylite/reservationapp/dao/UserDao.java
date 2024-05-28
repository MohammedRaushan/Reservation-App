package org.skylite.reservationapp.dao;

import java.util.Optional;

import org.skylite.reservationapp.model.User;
import org.skylite.reservationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUser(int id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public Optional<User> verifyByPhoneAndPassword(long phone, String password){
		return userRepository.findByPhoneAndPassword(phone, password);
	}
	
	public Optional<User> verifyByEmailAndPassword(String email, String password){
		return userRepository.findByEmailAndPassword(email, password);
	}
	
}
