package com.example.io.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.io.entity.Student;

@FunctionalInterface
public interface ExcelReader {

	List<Student> readExcel(InputStream inputStream) throws IOException;
}
