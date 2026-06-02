package com.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.model.StudentModel;
import com.ai.service.StudnetService;

@RestController
public class HomePageController {
	@Autowired
	public StudnetService studentService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello, welcome to the AI Gemini LLM Spring Boot application! svs";
	}
	 
	 
    @GetMapping("/students")
    @CrossOrigin("*")
    public List<StudentModel> getAllProducts() {
        System.out.println("Fetching all products..."); 
        return studentService.getAllStudents();
    }
    
    @PostMapping("/students")
    @CrossOrigin("*")
    public void addStudent(@RequestBody StudentModel student) {
		System.out.println("Adding new student: " + student); 
		studentService.addStudent(student);
	}
    
    
    @PutMapping("/students/{studentId}")
    @CrossOrigin("*")
    public void updateStudent(@PathVariable int studentId, @RequestBody StudentModel student) {
    			System.out.println("Updating student ID: " + studentId);
    }
    
    
    @DeleteMapping("/students/{studentId}")
    @CrossOrigin("*")
    public void deleteStudent(@PathVariable int studentId) {
    	
				System.out.println("Deleting student ID: " + studentId); 
	}
    
    
    
	 
}
