package org.skylite.reservationapp.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BusRequest {
	private String name;
	private LocalDate dateOfDeparture;
	private String busNo;
	private String fromLoc;
	private String toLoc;
	private int numberOfSeats;
}
