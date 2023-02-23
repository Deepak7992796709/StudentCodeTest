package com.imaginnovate.CodingTest.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id",nullable=false)
	private Long id;
	
	@Column(name="student_fname")
	private String firstName;
	
	@Column(name="student_lname")
	private String lastName;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="section")
	private String section;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="marks1")
	private Integer marks1;
	
	@Column(name="marks2")
	private Integer marks2;
	
	@Column(name="marks3")
	private Integer marks3;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="average")
	private Double average;
	
	@Column(name="result")
	private String result;

	
}
