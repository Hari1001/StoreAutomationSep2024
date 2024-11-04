package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import SA.StoreAutomation.Log;

import SA.StoreAutomation.BaseClass;
import SA.StoreAutomation.DataBinding;
import SA.StoreAutomation.Utils;

public class ProductPage extends BaseClass {

	@FindBy(xpath = "(//img[@title='MacBook Pro'])[1]")
	public WebElement macBook;
	
	@FindBy(xpath="(//h2)[2]")
	public WebElement price;

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Order History")
	public WebElement orderHistory;


	@FindBy(linkText = "Brands")
	public WebElement brands;
	
	@FindBy(id="button-cart")
	public WebElement addToCart;
	
	@FindBy(partialLinkText= "shopping cart")
	public WebElement shoppingCart;
	
	HomePage home = new HomePage();
	DataBinding data = new DataBinding();
	
	@Test
	public void productVerification()
	{
		home.verfiyTheLaptopsPrice();
		//Utils.doubleClick(macBook);
		String text=Utils.getText(price);
		data.setPrice(text);		
		
	//	DataBinding(text);
		String value=data.getPrice();
		System.out.println(value);
		Utils.scrollToElement(orderHistory);
		//Utils.jsExecute(orderHistory);
	//	Utils.rightClick(brands);
		//Utils.clickOnElement(brands);
		Utils.KeySEnter(brands);
		Utils.screenshot("Parent");
		Utils.moveToWindow();
		Utils.clickOnElement(addToCart);
		Utils.clickOnElement(shoppingCart);
		Log.endTestCase("Product page");
	}
}
