package com.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	
	//Initialize Variable(s)
	private static XSSFWorkbook ExcelWBook;
	
	//Location of the Excel File
	public static String path;
	public static String sheetName = "";

	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//Set the current/active Excel File
	public void setDataTablePath(String dataTablePath) {
		path = dataTablePath;
	}
	
	//Set the current/active Excel Sheet
	public void setSheetName(String desiredSheetName) {
		sheetName = desiredSheetName;
	}


	@DataProvider(name="inputs")
	public Object[] getDataWithTable() throws IOException {

		FileInputStream fs = new FileInputStream(path);
		ExcelWBook = new XSSFWorkbook(fs);
		XSSFSheet sheet = ExcelWBook.getSheet(sheetName);

		Object[] data = new Object[sheet.getLastRowNum()];

		Map<String,String> table;

		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			table = new HashMap<>();
			for(int j=0; j<sheet.getRow(0).getLastCellNum();j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				table.put(key, value);
				data[i-1] = table;
			}
		}
		return data;
	}


	//Send output to a specific DataSheet cell
	public static void setDataTableCell(String result, int rowNum, int colNum) {
		//Initialize Variable(s)
		colNum = colNum - 1; //Reduce the colNum by 1, since the ExcelSheet starts with 0. (no need to do this with rowNum, since the rows have row #0 dedicated to column titles)

		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			System.out.println(path);
			//Access the Excel DataSheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			System.out.println(sheetName);
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);

			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}

			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);

			ExcelWBook.write(outputFile);
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}