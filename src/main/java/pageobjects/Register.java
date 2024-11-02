package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import SA.StoreAutomation.BaseClass;
import SA.StoreAutomation.Utils;

public class Register extends BaseClass {

	@FindBy(xpath = "//span[text()='My Account']")
	public WebElement myAccount;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
	public WebElement register;

	@FindBy(id = "input-firstname")
	public WebElement firstName;

	@FindBy(xpath = "//label[text()='Subscribe']/following-sibling::div//label//input[@value='0']")
	public WebElement noRadioButton;

	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement checkBox;

	public Register() {
		PageFactory.initElements(driver, this);
	}

	@Test
	public void login() throws InterruptedException {
		Thread.sleep(4000);
		Utils.clickOnElement(myAccount);
		Thread.sleep(2000);
		register.click();
		Thread.sleep(2000);
		Utils.sendKeysToElement(firstName, "Sathish");

		WebElement lastName = driver.findElement(By.id("input-lastname"));
		lastName.clear();
		lastName.sendKeys("sheelam");
		WebElement email = driver.findElement(By.id("input-email"));
		email.clear();
		email.sendKeys("sheelamsathish@gmail.com");
		WebElement telephone = driver.findElement(By.id("input-telephone"));
		telephone.clear();
		telephone.sendKeys("9642223520");
		WebElement password = driver.findElement(By.id("input-password"));
		password.clear();
		password.sendKeys("sathish@123");
		WebElement passwordConfirm = driver.findElement(By.id("input-confirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("sathish@123");
		noRadioButton.click();
		checkBox.click();
		Thread.sleep(5000);
	}
}
