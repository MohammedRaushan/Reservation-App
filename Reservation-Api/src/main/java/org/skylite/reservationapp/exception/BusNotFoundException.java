package org.skylite.reservationapp.exception;

@SuppressWarnings("serial")
public class BusNotFoundException extends RuntimeException {
	public BusNotFoundException(String message) {
		super(message);
	}
}
