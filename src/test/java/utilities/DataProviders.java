package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	
	// Dataprovider 1
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException
	{
		//String path = ".\\testData\\Opencart_LoginData.xlsx"; 
		String path = ".\\testData\\Opencart_Logindata2.xlsx";
		ExcelUtility xlutils = new ExcelUtility(path); // excelutil are non-static we have to create obj to call its methods
		
		int total_rows = xlutils.getRowCount("Sheet1");
		int total_cols = xlutils.getCellCount("Sheet1", 1);
		
		String[][] logindata = new String[total_rows][total_cols]; // created 2 dimensional array which store xl values
		
		for(int r=1; r<=total_rows; r++)
		{
			for(int c=0; c<total_cols; c++)
			{
				logindata[r-1][c] = xlutils.getCellData("Sheet1", r, c); //[r-1] --> while storing we have to store according to array index concept which starts from row=0, col=0
			}
		}
		return logindata;  // return 2 dimensional array
		
	}
	
	
	// Dataprovider 2 we can add dataproviders acc to our TC
	// Dataprovider 3
	// Dataprovider 4

}
