package com.example.io.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.example.io.entity.Student;
import com.example.io.utility.ExcelUtils;

public class HSSFExcelReader implements ExcelReader {

	@Override
	public List<Student> readExcel(InputStream inputStream) throws IOException {
		try (Workbook workbook = new HSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
//			sheet.forEach(row -> row.forEach(cell -> System.out.print(cell + "\t")));
			System.out.println("From HSSFExcelReader class");
			return ExcelUtils.convertExcelToList(sheet);
		}
	}

}
