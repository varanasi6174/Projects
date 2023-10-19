package com.librarymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="UsersInfo")
@Data

public class User {
	@Id
	private int uid;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "User Name cannot be blank")
	private String uname;
	
	@Column(length = 20, nullable = false)
	@NotBlank(message = "User Surname cannot be blank")
	private String usurname;
	
	@Column(length = 26, nullable = false, unique = true)
	@NotBlank(message = "User Email ID cannot be blank")
	@Email(message = "Email ID is not proper")
	private String uemail;
	
	@Column(length = 11, nullable = false, unique = true)
	@NotNull(message = "Phone number cannot be Null")
	private long uphone;

}
