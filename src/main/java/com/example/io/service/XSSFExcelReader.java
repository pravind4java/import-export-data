package com.example.io.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.io.entity.Student;
import com.example.io.utility.ExcelUtils;

public class XSSFExcelReader implements ExcelReader {

	@Override
	public List<Student> readExcel(InputStream inputStream) throws IOException {
		try (Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
//			sheet.forEach(row -> row.forEach(cell -> System.out.print(cell + "\t")));
			System.out.println("From XSSFExcelReader class");
			return ExcelUtils.convertExcelToList(sheet);
		}
	}

}
