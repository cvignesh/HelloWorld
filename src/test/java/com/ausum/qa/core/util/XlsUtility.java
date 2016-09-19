package com.ausum.qa.core.util;

public class XlsUtility {
	
	public static Object[][] getData(Xls_Reader xls, String testCaseName)
	{
		if(!xls.isSheetExist(testCaseName))
		{
			xls = null;
			return new Object[1][0];
		}
		
		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);
		
		Object[][] data = new Object[rows-1][cols];
		for(int rowNum = 2;rowNum <=rows; rowNum++)
		{
		   for(int colNum =0;colNum <cols;colNum++)
		   {
			   data[rowNum-2][colNum]=xls.getCellData(testCaseName, colNum, rowNum);
		   }
	    }
		return data;
	}
}
