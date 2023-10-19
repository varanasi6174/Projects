package com.librarymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="BooksInfo")
@Data
public class Book {
	
	@Id
	private int bid;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Book Name cannot be blank")
	private String bname;
	
	@Column( nullable = false)
	@NotNull(message = "Book price cannot be blank")
	private double bprice;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Book publication cannot be blank")
	private String bpublication;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Book category cannot be blank")
	private String bcategory;
	
	@Column(length = 25, nullable = false)
	@NotNull(message = "Availability cannot be blank")
	private int bavailable;

}
