package org.skylite.reservationapp.dto;

import lombok.Data;

@Data
public class AdminRequest {
	private String name;
	private long phone;
	private String gst_number;
	private String email;
	private String password;
	private String travels_name;
}
