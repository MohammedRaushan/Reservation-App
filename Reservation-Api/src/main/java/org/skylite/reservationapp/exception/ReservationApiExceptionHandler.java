package org.skylite.reservationapp.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.skylite.reservationapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationApiExceptionHandler {
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleANFE(AdminNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Admin Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handleIntegrityException(SQLIntegrityConstraintViolationException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Data already exists");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structure);
	}
	
//	User based Exceptions
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUNFE(UserNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("User Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
//	Bus based Exceptions
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBNFE(BusNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Bus Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	@ExceptionHandler(NoBusPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleNBPE(NoBusPresentException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("No bus found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
}








