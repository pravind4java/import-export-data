package com.example.io.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.io.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Serializable> {

}
