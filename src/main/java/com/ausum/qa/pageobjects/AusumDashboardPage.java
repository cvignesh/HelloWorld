package com.ausum.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.ausum.qa.core.BasePage;

public class AusumDashboardPage extends BasePage{

	@FindBy(xpath="//li[@class='menu-bar']//span[contains(text(), 'Surveys')]")
	private WebElement linkSurveyTab;
	
	@FindBy(xpath="//li[@class='menu-bar']//span[contains(text(), 'Add New Survey')]")
	private WebElement linkAddSurvey;
	
	public void hoverAndClickAddSurvey()
	{
		Actions act = new Actions(driver);
		act.moveToElement(linkSurveyTab);
		act.click(linkAddSurvey);
		act.build().perform();
	}
}
