package com.ausum.qa.seleniumtests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ausum.qa.core.BaseTest;
import com.ausum.qa.core.util.XlsUtility;
import com.ausum.qa.core.util.Xls_Reader;
import com.ausum.qa.pageobjects.AusumDashboardPage;
import com.ausum.qa.pageobjects.AusumLoginPage;

public class LoginSuccessTest extends BaseTest{

	private Xls_Reader excelReader = new Xls_Reader(System.getProperty("user.dir") + "\\resources\\TestData\\TestDataSheet.xlsx");
	
	@Test(dataProvider="getData")
	public void test(String userName, String passWord, String imageId)
	{
		AusumLoginPage ausumLoginPage = PageFactory.initElements(driver, AusumLoginPage.class);
		ausumLoginPage.typeUserName(userName);
		ausumLoginPage.typePassword(passWord);
		AusumDashboardPage ausumDashboardPage = ausumLoginPage.selectSecurityImageAndNavigate(imageId);
		ausumDashboardPage.hoverAndClickAddSurvey();
		//hardAssert.assertTrue(ausumLoginPage.validateLogin(),"Login Not Successfull");
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return XlsUtility.getData(excelReader, this.getClass().getSimpleName());
	}
	
}
