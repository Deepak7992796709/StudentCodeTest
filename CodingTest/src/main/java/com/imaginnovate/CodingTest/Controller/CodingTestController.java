package com.imaginnovate.CodingTest.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.CodingTest.Entity.Student;
import com.imaginnovate.CodingTest.pojo.StudentPojo;
import com.imaginnovate.CodingTest.pojo.UpdateStuentPojo;
import com.imaginnovate.CodingTest.repository.StudentRepository;

@RestController
public class CodingTestController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/saveStudent")
    ResponseEntity<?> addStudent(@Valid @RequestBody StudentPojo studentPojo,BindingResult bindingResult) {
		 Map<String, String> maps = new HashMap<>();
		 Student studentEntity=new Student();
		 Integer age=0;
		 String result="";
		 Integer total=0;
		 double average=0.00;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(studentPojo.toString());
		  
		if (bindingResult.hasErrors()) { //it will check the validation of pojo class 
			bindingResult.getAllErrors().forEach((error) -> {
			        String fieldName = ((FieldError) error).getField();
			        String errorMessage = error.getDefaultMessage();
			        maps.put(fieldName, errorMessage);
			    });
            return  ResponseEntity.ok(maps);
		}
		LocalDate dob = LocalDate.parse(studentPojo.getDob()); //Here calculate the age through dob     		    
		LocalDate curDate = LocalDate.now();  //now() method obtains the current date from the system clock in the default time zone  		 
		if ((dob != null) && (curDate != null))   
		{  
		age= Period.between(dob, curDate).getYears(); //calculates the amount of time between two dates and returns the years 
		} 
		
		if(age<15 || age>20) {//here check the validation of age
			return  ResponseEntity.ok("Age should be greater than 15 year and less than or equal to 20 years.");
		}
		if(studentPojo.getMarks1()<35) { //here check the validation of marks 
			result="failed";
		}else if(studentPojo.getMarks2()<35) {
			result="failed";
		}else if(studentPojo.getMarks3()<35) {
			result="failed";
		}else {
			result="passed";
		}
		
		if(result.equalsIgnoreCase("passed")) { //if result should be passed then total and average calculate
			total=studentPojo.getMarks1()+studentPojo.getMarks2()+studentPojo.getMarks3();
			average=(double) (total/3);
			System.out.println(average);
		}
		try {
			studentEntity.setDob(sdf.parse(studentPojo.getDob()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentEntity.setTotal(total);
		studentEntity.setAverage(average);
		studentEntity.setResult(result);
		BeanUtils.copyProperties(studentPojo,studentEntity);// here copy from the pojo class property to entity class
		studentEntity=studentRepository.save(studentEntity); // save the entity in database
		
		if(studentEntity !=null) {
		maps.put("success", "Save Successfully");
		}
        return ResponseEntity.ok(maps);
    }

	@PostMapping("/updateStudent")
    ResponseEntity<?> updateStudent(@Valid @RequestBody UpdateStuentPojo updateStuentPojo,BindingResult bindingResult) {
		 Map<String, String> maps = new HashMap<>();
		 Optional<Student> opstudentEntity=null;
		 Student studentEntity=null;
		 Integer age=0;
		 String result="";
		 Integer total=0;
		 Double average=0.00;
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach((error) -> {
			        String fieldName = ((FieldError) error).getField();
			        String errorMessage = error.getDefaultMessage();
			        maps.put(fieldName, errorMessage);
			    });
            return  ResponseEntity.ok(maps);
		}
		if(updateStuentPojo.getId()!=null) {
			opstudentEntity=studentRepository.findById(updateStuentPojo.getId());
    	}
		studentEntity=opstudentEntity.get();
		if(updateStuentPojo.getMarks1()<35) { //here check the validation of marks 
			result="failed";
		}else if(updateStuentPojo.getMarks2()<35) {
			result="failed";
		}else if(updateStuentPojo.getMarks3()<35) {
			result="failed";
		}else {
			result="passed";
		}
		
		if(result.equalsIgnoreCase("passed")) { //if result should be passed then total and average calculate
			total=updateStuentPojo.getMarks1()+updateStuentPojo.getMarks2()+updateStuentPojo.getMarks3();
			average=(double) (total/3);
			System.out.println(average);
		}
		studentEntity.setTotal(total);
		studentEntity.setAverage(average);
		studentEntity.setResult(result);
		BeanUtils.copyProperties(updateStuentPojo,studentEntity);
		studentRepository.save(studentEntity);
		return ResponseEntity.ok("Update Successfully");
	}
}
