package com.ausum.qa.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	
	public String path;
	public InputStream fis = null;
	public FileOutputStream fileOut = null;	
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private XSSFWorkbook workbook;
	
	public Xls_Reader(String path)
	{
		this.path = path;
		try
		{
			fis = this.getClass().getClassLoader().getResourceAsStream(path);
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}
		catch(Exception e)
		{		
					
		}
	}
	
	public int getRowCount(String SheetName)
	{
		int index = workbook.getSheetIndex(SheetName);
		if(index == -1)
			return 0;
		else
		{
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}
	
	public int getColumnCount(String sheetName)
	{
		if(!isSheetExist(sheetName))
			return -1;
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		if(row == null)
		{
			return -1;
		}
		return row.getLastCellNum();
     }
	
	public boolean isSheetExist(String sheetName)
	{
		int index = workbook.getSheetIndex(sheetName);
		if(index == -1)
		{
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if(index == -1)
				return false;
			else
				return true;
		}
		else
			return true;
				
	}
	
	public String getCellData(String sheetName,int colNum, int rowNum)
	{
		try
		{
			if(rowNum <= 0)
				return "";
			
			int index = workbook.getSheetIndex(sheetName);
			if(index == -1)
				return "";
			
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if(row == null)
				return "";
			cell = row.getCell(colNum);
			if(cell == null)
				return "";
			
			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || 
					cell.getCellType() == Cell.CELL_TYPE_FORMULA)
			{
				String cellText = String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" +
							   cal.get(Calendar.DAY_OF_MONTH) + "/" +
							   cellText;
				}
			    return cellText;
			}
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e)
		{
			return "row "+rowNum+" or column "+colNum+" does not exist in xls";
		}
			
	}
}
