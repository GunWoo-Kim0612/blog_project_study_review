package com.gwk.review.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30,nullable = false, unique = true)
	private String username;
	
	@Column(length = 100,nullable = false)
	private String password;
	
	@Column(length = 50,nullable = false)
	private String email;
	
	@ColumnDefault("'USER'")
	private String role;
	
	@CreationTimestamp
	private Timestamp createDime;
	
	@CreationTimestamp
	private Timestamp updateDime;
}
