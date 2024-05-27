package org.skylite.reservationapp.dto;

import lombok.Data;

@Data
public class UserRequest {
	private String name;
	private long phone;
	private String email;
	private String password;
	private int age;
	private String gender;
}
