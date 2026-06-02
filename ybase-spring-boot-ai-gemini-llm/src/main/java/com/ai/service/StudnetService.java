package com.ai.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.model.StudentModel;
import com.ai.repository.StudentRepository;

@Service
public class StudnetService {

	@Autowired
	private StudentRepository studentrepository;
	
 
    private final List<StudentModel> ListofStudentModel = new ArrayList<>(Arrays.asList(
            new StudentModel(1L, "Ravi" ),
            new StudentModel(2L, "suresh"  ),
            new StudentModel(3L, "Naresh" )
    ));
    
    // Get all Students
	
	public List<StudentModel> getAllStudents() {
		//return ListofStudentModel;
		if(studentrepository.findAll().isEmpty()) {
			// If the repository is empty, populate it with initial ListofStudentModel
			return ListofStudentModel;
		}
		return studentrepository.findAll();
		}
    
	
	//Save all students 
	public void addStudent(com.ai.model.StudentModel student) {
		
		studentrepository.save(student);
	}
	
	 // Update student by ID
	void updateStudent(StudentModel student) {
		studentrepository.save(student);
	}
	
	// Update student by ID
	
	void deleteStudent(Long id) {
		studentrepository.deleteById(id);
	}
	
} 