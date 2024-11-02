package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import SA.StoreAutomation.BaseClass;
import SA.StoreAutomation.Utils;

public class HomePage extends BaseClass{

	
	@FindBy(xpath="//a[text()='Laptops & Notebooks']")
	public WebElement laptopsNotes;
	
	@FindBy(xpath="//a[text()='Show All Laptops & Notebooks']")
	public WebElement showAll;
	
	
	@FindBy(xpath="//select[@id='input-sort']")
	public WebElement  sortBy;
	
	@FindBy(xpath="//select[@id='input-limit']")
	public WebElement showDropdown;
	
	@FindBy(linkText = "MacBook Pro")
	public WebElement macBookPro;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public  void verfiyTheLaptopsPrice()
	{
		
		Login login = new Login();
		login.login();
		Utils.mouseHover(laptopsNotes);
		Utils.clickOnElement(showAll);
		Utils.clickOnElement(sortBy);
		Utils.selectByValue(sortBy, "Price (High > Low)");
		Utils.clickOnElement(showDropdown);
		Utils.selectByIndex(showDropdown, 3);
		Utils.implicitWait(10);
		Utils.clickOnElement(macBookPro);
	}
}
