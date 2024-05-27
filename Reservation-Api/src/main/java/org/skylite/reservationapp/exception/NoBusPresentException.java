package org.skylite.reservationapp.exception;

@SuppressWarnings("serial")
public class NoBusPresentException extends RuntimeException{
	public NoBusPresentException(String message) {
		super(message);
	}
}
