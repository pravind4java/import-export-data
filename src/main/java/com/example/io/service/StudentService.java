package com.example.io.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.io.entity.Student;
import com.example.io.repository.StudentRepository;
import com.example.io.utils.PoiUtils;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;


	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	public void generateExcel(HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Student Details");
		XSSFRow hearderRow = sheet.createRow(0);

		Cell cell0 = hearderRow.createCell(0);
		cell0.setCellStyle(PoiUtils.boldFontStyle(workbook));
		cell0.setCellValue("First Name");

		Cell cell1 = hearderRow.createCell(1);
		cell1.setCellStyle(PoiUtils.boldFontStyle(workbook));
		cell1.setCellValue("Last Name");

		Cell cell2 = hearderRow.createCell(2);
		cell2.setCellStyle(PoiUtils.boldFontStyle(workbook));
		cell2.setCellValue("Age");

		List<Student> students = studentRepository.findAll();

		int dataRowIndex = 1;

		for (Student stud : students) {
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(stud.getFirstName());
			dataRow.createCell(1).setCellValue(stud.getLastName());
			dataRow.createCell(2).setCellValue(stud.getAge());
			dataRowIndex++;
		}

		ServletOutputStream sos = response.getOutputStream();
		workbook.write(sos);
		workbook.close();
		sos.close();

	}

	public void saveImportExcel(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		try {
			ExcelReader reader = ExcelReaderFactory.getReader(extension);
			List<Student> students = reader.readExcel(file.getInputStream());
			studentRepository.saveAll(students);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
