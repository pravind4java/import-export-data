package com.example.io.service;

public class ExcelReaderFactory {

	public static ExcelReader getReader(String extension) {
		switch (extension.toLowerCase()) {
		case "xls":
			return new HSSFExcelReader();
		case "xlsx":
			return new XSSFExcelReader();
		default:
			throw new IllegalArgumentException("Unsupported file extension: " + extension);
		}
	}
}
