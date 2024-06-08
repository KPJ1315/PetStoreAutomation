package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fileInputStream;
	public FileOutputStream fileOutputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		int rowCount =sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		return cellCount;
	}
	
	public String getcellData(String sheetName, int rowNum, int column) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(column);
		
		DataFormatter dataFormatter = new DataFormatter();
		String data;
		try {
			data = dataFormatter.formatCellValue(cell);
		} catch (Exception e) {
			// TODO: handle exception
			data="";
		}
		workbook.close();
		fileInputStream.close();
		return data;
	}
	
	public void setCrellData(String  sheetName,int rowNum, int column,String data) throws IOException{
		File file = new File(path);
		if(!file.exists()) {
			workbook = new XSSFWorkbook();
			fileOutputStream = new FileOutputStream(path);
			workbook.write(fileOutputStream);
		}
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet();
			sheet = workbook.getSheet(sheetName);
		}
		 
		if(sheet.getRow(rowNum)==null) {
			sheet.createRow(rowNum);
			row = sheet.getRow(rowNum);
			cell = row.createCell(column);
			cell.setCellValue(data);
			fileOutputStream = new FileOutputStream(path);
			workbook.write(fileOutputStream);
			workbook.close();
			fileInputStream.close();
			fileOutputStream.close();
		}
	}
	
	public void fillGreenColor(String  sheetName,int rowNum, int columnNum) throws IOException{
		fileInputStream = new FileInputStream(path);
		workbook= new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		  // Get the row and cell
        row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        cell = row.getCell(columnNum);
        if (cell == null) {
            cell = row.createCell(columnNum);
        }

        // Create a cell style with green background color
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Apply the style to the cell
        cell.setCellStyle(style);

        // Close the input stream
        fileInputStream.close();

        // Write the changes to the file
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);

        // Close the output stream and workbook
        fileOutputStream.close();
        workbook.close();
    }
	
	public void fillRedColor(String  sheetName,int rowNum, int columnNum) throws IOException{
		fileInputStream = new FileInputStream(path);
		workbook= new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		  // Get the row and cell
        row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        cell = row.getCell(columnNum);
        if (cell == null) {
            cell = row.createCell(columnNum);
        }

        // Create a cell style with green background color
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Apply the style to the cell
        cell.setCellStyle(style);

        // Close the input stream
        fileInputStream.close();

        // Write the changes to the file
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);

        // Close the output stream and workbook
        fileOutputStream.close();
        workbook.close();
    }


}
