package com.qrcode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrcode.entities.Student;
import com.qrcode.qrcode.QrCodeGenrated;
import com.qrcode.srevices.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getStudent() {
		List<Student>students = studentService.getStudents();
		if(students.size()!=0) {
			for (Student student:students) {
				QrCodeGenrated.generateQrcode(student);
			}
		}
		return ResponseEntity.ok(studentService.getStudents());
	}

	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@GetMapping("/{id}")
	public Student findById(@PathVariable("id") Long id) {
		return studentService.findById(id);
	}
}
