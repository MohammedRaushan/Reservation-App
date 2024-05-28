package org.skylite.reservationapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
	@NotBlank(message = "Name is mandatory")
	private String name;
	private long phone;
	@NotBlank(message = "GST Number is mandatory")
	@Size(min = 15, max = 15, message = "GST Number must have 15 characters.")
	private String gst_number;
	@Email(message = "Invalid Format")
	private String email;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@NotBlank(message = "Travels Name is mandatory")
	private String travels_name;
}
