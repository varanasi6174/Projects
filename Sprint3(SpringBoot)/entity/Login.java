package com.librarymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Table(name="LoginInfo")

@Entity

public class Login {
	@Id
	private int uid;

	@Column(length=15, nullable=false)
	@NotBlank(message= "message can not be blank")
	private String userName;
	
	@Column(length=15, nullable=false)
	@NotBlank(message= "message can not be blank")
	private String password;



}
