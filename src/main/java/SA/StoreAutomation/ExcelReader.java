package SA.StoreAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {

		XSSFWorkbook workbook; // Excell 
		XSSFSheet sheet;   // Sheet
		XSSFRow row; // Row
		XSSFCell cell; 
		FileInputStream fis; // Reading the data from 
		FileOutputStream fout; // Writing the data from Excel
		//D:\Testing\StoreAutomation
		public String path = "D:\\Testing\\StoreAutomation\\TestData.xlsx";

		public ExcelReader() { // have to this method every time whenever i am 
			// I dont want to call explicitly
			try {

				fis = new FileInputStream(path);
				workbook = new XSSFWorkbook(fis);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		@DataProvider(name ="loginCredentials")
		public Object[][] read()
		{
			
			Object[][] data = readExcel();
			return data;
			
		}
		
		
		public  Object[][] readExcel()
		{
		
			int rows= sheet.getLastRowNum();
			int cols=sheet.getRow(0).getLastCellNum();
			
			Object[][] data= new Object[rows][cols];
			for(int i=0;i<rows;i++)
			{
				 row= sheet.getRow(i+1);
				 for(int j=0;j<cols;j++)
				 {
					cell= row.getCell(j);
					CellType type=cell.getCellType();
					switch(type)
					{
					case STRING:
						data[i][j]=cell.getStringCellValue();
						break;
						
					case NUMERIC:
						data[i][j] = Integer.toString((int)cell.getNumericCellValue());
						break;
					}
				 }	
			}
			return data;
		}
		public String readData(String sheetName, int row, int col) {
			
			sheet=workbook.getSheet(sheetName);
			String data=sheet.getRow(row).getCell(col).getStringCellValue();
			return data;
		}

		public void writeExcel(String sheetName, String cellValue, int row, int col) throws IOException {

			FileInputStream fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			sheet.getRow(row).createCell(col).setCellValue(cellValue);
			fout = new FileOutputStream(path);
			workbook.write(fout);
			fout.close();
			workbook.close();
		}

		public int getRowCount(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			sheet= workbook.getSheetAt(index);
			int number =sheet.getLastRowNum()+1;
			return number;
			
//			int index = workbook.getSheetIndex(sheetName);
//			sheet = workbook.getSheetAt(index);
//			int number = sheet.getLastRowNum() + 1;
//			return number;
		}

		public int getColCount(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(index);
			int col= row.getLastCellNum();
			return col;
		}

		public void addSheet() throws IOException

		{
			try {
				fout = new FileOutputStream(path);
				workbook.createSheet("Thursday batch");
				workbook.write(fout);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
			public Object[][] getTestData(String sheetName) {
			int row = getRowCount(sheetName);
			System.out.println("Rows : "+row);
			int col = getColCount(sheetName);
			System.out.println("Columns : "+col);
			Object[][] data = new Object[sheet.getLastRowNum()][col];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j< col; j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
					System.out.println(data[i][j]);
				}
			}
			return data;
		}
			
		public void removeSheet() throws IOException

		{
			try {
				workbook.removeSheetAt(1);
				workbook.write(fout);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
