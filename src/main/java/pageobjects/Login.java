package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import SA.StoreAutomation.BaseClass;
import SA.StoreAutomation.Utils;

public class Login extends BaseClass {

	@FindBy(xpath = "//span[text()='My Account']")
	public WebElement myAccount;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
	public WebElement login;

	@FindBy(id = "input-email")
	public WebElement userName;

	@FindBy(id = "input-password")
	public WebElement passowrd;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement loginButton;

	@FindBy(xpath = "//a[text()='Account']")
	public WebElement account;

	public Login() {
		PageFactory.initElements(driver, this);
	}

	@Test
	public void login() {
		
		Utils util= new Utils();
	//	Utils.clickOnElement(account);
		
		Utils.clickOnElement(myAccount);
		Utils.clickOnElement(login);
	//	String username = pro.getProperty("userName");
		//String password = pro.getProperty("password");
		Utils.sendKeysToElement(userName, pro.getProperty("userName"));
		Utils.sendKeysToElement(passowrd, pro.getProperty("password"));
		Utils.clickOnElement(loginButton);
		Utils.waitForElementVisible(account, 5);
	}
}
