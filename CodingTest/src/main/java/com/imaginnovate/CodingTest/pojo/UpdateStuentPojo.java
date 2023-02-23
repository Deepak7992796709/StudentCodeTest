package com.imaginnovate.CodingTest.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class UpdateStuentPojo {

	private Long id;
	
	@NotNull(message="marks1 should not be blank")
	@Min(value =0, message = "marks1 must be greater than 0")
	@Max(value = 100, message = "marks1 must be smaller than 100")
	private Integer marks1;
	
	@NotNull(message="marks2 should not be blank")
	@Min(value =0, message = "marks2 must be greater than 0")
	@Max(value = 100, message = "marks2 must be smaller than 100")
	private Integer marks2;
	
	@NotNull(message="marks3 should not be blank")
	@Min(value =0, message = "marks3 must be greater than 0")
	@Max(value = 100, message = "marks3 must be smaller than 100")
	private Integer marks3;
}
