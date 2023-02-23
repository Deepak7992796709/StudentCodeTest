package com.imaginnovate.CodingTest.pojo;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentPojo {

	@Pattern(regexp="^[a-zA-Z]{3,30}",message="firstName length must be 3")  
	private String firstName;
	
	@Pattern(regexp="^[a-zA-Z]{3,30}",message="lastName length must be 3")  
	private String lastName;
	
	@NotNull(message = "dob is mandatory")
	private String dob;
	
	@Pattern(regexp = "^[ABC]$", message = "Section must be either A, B or C")
	private String section;
	
	@Pattern(regexp = "^[MF]$", message = "Gender must be either M or F")
	private String gender;	
	
	@Min(value =0, message = "marks1 must be greater than 0")
	@Max(value = 100, message = "marks1 must be smaller than 100")
	private int marks1;
	
	@Min(value =0, message = "marks2 must be greater than 0")
	@Max(value = 100, message = "marks2 must be smaller than 100")
	private int marks2;
	
	@Min(value =0, message = "marks3 must be greater than 0")
	@Max(value = 100, message = "marks3 must be smaller than 100")
	private int marks3;

}
