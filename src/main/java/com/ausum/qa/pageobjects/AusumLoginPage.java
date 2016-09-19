package com.ausum.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ausum.qa.core.BasePage;

public class AusumLoginPage extends BasePage {
	
	@FindBy(xpath="//input[@name='Username']")
	private WebElement lblUserName;
	
	@FindBy(xpath="//input[@name='Password1']")
	private WebElement lblPassword;
	
	public void typeUserName(String strUserName)
	{
		lblUserName.sendKeys(strUserName);
	}
	
	public void typePassword(String strPassword)
	{
		lblPassword.sendKeys(strPassword);
	}

	public AusumDashboardPage selectSecurityImageAndNavigate(String strImageId)
	{
		driver.findElement(By.xpath("//img[@imageid='"+strImageId+"']")).click();
		return PageFactory.initElements(driver, AusumDashboardPage.class);
	}
	
//	public boolean validateLogin()
//	{
//		return driver.getCurrentUrl().contains("dashboard.cfm");		
//	}
}
