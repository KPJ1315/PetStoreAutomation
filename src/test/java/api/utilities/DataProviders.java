package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String [][]getAllData() throws IOException{
		String path = System.getProperty("user.dir")+"/Testdata/TDD_ExcelData.xlsx"; //Excel file path in the project
		ExcelUtility excelUtility = new ExcelUtility(path);
		
		int rowNum = excelUtility.getRowCount("Sheet1");
		int colCount = excelUtility.getCellCount("Sheet1", 1);
		
		String [][]apiData = new String[rowNum][colCount];
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colCount;j++) {
				apiData[i-1][j]=excelUtility.getcellData("Sheet1", i, j);
			}
		}
		return apiData;
	}

	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir")+"//Testdata//TDD_ExcelData.xlsx"; //Excel file path in the project
		ExcelUtility excelUtility = new ExcelUtility(path);
		int rowNum = excelUtility.getRowCount("Sheet1");
		String apiData[] = new String[rowNum];
		for(int i=1;i<=rowNum;i++) {
			apiData[i-1]=excelUtility.getcellData("Sheet1", i, 1);
		}
		return apiData;
		
	}
	
}
