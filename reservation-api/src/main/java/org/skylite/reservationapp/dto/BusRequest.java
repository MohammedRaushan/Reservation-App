package org.skylite.reservationapp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BusRequest {
	@NotBlank(message = "Name is mandatory")
	private String name;
	private LocalDate dateOfDeparture;
	@NotBlank(message = "Bus Number is mandatory")
	private String busNo;
	@NotBlank(message = "From Location is mandatory")
	private String fromLoc;
	@NotBlank(message = "To Location is mandatory")
	private String toLoc;
	private int numberOfSeats;
}
