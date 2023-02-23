package com.imaginnovate.CodingTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.CodingTest.Entity.Student;
import java.lang.Long;
import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
