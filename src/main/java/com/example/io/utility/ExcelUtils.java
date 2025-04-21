package com.example.io.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import com.example.io.entity.Student;

public class ExcelUtils {

	// Check that file's type excel or not.
	public static boolean isExcelFileToImport(MultipartFile file) {
		String contentType = file.getContentType();
		if (("application/vnd.ms-excel").equals(contentType)
				|| ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet").equals(contentType)) {
			return true;
		} else {
			return false;
		}
	}

	public static List<Student> convertExcelToList(Sheet sheet) throws IOException {
		List<Student> students = new ArrayList<>();
		Iterator<Row> itr = null;
		try {

			itr = sheet.iterator();
			int rowNumber = 0;
			while (itr.hasNext()) {
				Row row = itr.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellItr = row.iterator();
				int cellCount = 0;
				Student s = new Student();
				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					switch (cellCount) {
					case 0:
						s.setFirstName(cell.getStringCellValue());
						break;
					case 1:
						s.setLastName(cell.getStringCellValue());
						break;
					case 2:
						s.setAge((int) cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cellCount++;
				}
				students.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
