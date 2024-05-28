package org.skylite.reservationapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusResponse {
	private int id;
	private String name;
	private LocalDate dateOfDeparture;
	private String busNo;
	private String fromLoc;
	private String toLoc;
	private int numberOfSeats;
}
