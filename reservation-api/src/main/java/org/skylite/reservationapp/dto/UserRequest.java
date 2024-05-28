package org.skylite.reservationapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
	@NotBlank(message = "Name field is mandatory")
	private String name;
	private long phone;
	@NotBlank
	@Email(message = "Invalid Format")
	private String email;
	@NotBlank(message = "Password is manadatory")
	@Size(min = 8, max = 20)
	private String password;
	private int age;
	@NotBlank(message = "Gender is mandatory")
	private String gender;
}
