package com.qrcode.srevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrcode.entities.Student;
import com.qrcode.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public Student addStudent(Student student) {
		return studentRepository.save(student);
		}
	
	public Student findById(Long id) {
		
		return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
	}
}
