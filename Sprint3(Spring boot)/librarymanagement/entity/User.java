package com.librarymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data

@Entity
@Table(name = "user")
public class User {
	
    @Id
    private int uid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

}
