package com.qrcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qrcode.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


}
