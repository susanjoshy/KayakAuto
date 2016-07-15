package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtil {

	static Object[][] data = null;

	public static Object[][] read() {
		String path = String.format("%s/search-data.xlsx", System.getProperty("user.dir"));
		FileInputStream inputStream = null;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(path);

			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			int numberOfRows = sheet.getLastRowNum();
			int numberOfColumns = countNonEmptyColumns(sheet);
			data = new Object[numberOfRows][numberOfColumns];
			for (int rowNum = 1; rowNum < numberOfRows; rowNum++) {
				Row row = sheet.getRow(rowNum);
				for (int column = 0; column < numberOfColumns; column++) {
					Cell cell = row.getCell(column);
					data[rowNum][column] = cell.getStringCellValue();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	private static int countNonEmptyColumns(Sheet sheet) {
		Row firstRow = sheet.getRow(0);
		return firstEmptyCellPosition(firstRow);
	}

	private static int firstEmptyCellPosition(Row cells) {
		int columnCount = 0;
		for (Cell cell : cells) {
			if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}
			columnCount++;
		}
		return columnCount;

	}
}
