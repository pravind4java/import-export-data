package com.example.io.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.io.entity.Student;
import com.example.io.service.StudentService;
import com.example.io.utility.ExcelUtils;

@RestController
public class ReportRestController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Student> addStudent(@RequestBody Student stud) {
		return new ResponseEntity<Student>(studentService.addStudent(stud), HttpStatus.OK);
	}

	@GetMapping("/exportToExcel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=student-details.xlsx";
		response.setHeader(headerKey, headerValue);
		studentService.generateExcel(response);
	}

	@PostMapping("/importToExcel")
	public ResponseEntity<?> importToExcel(@RequestParam("file") MultipartFile file) {
		if (ExcelUtils.isExcelFileToImport(file)) {
			studentService.saveImportExcel(file);
			return ResponseEntity.ok("Excel file imported successfully.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file only for import data.");
	}

}
