package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import SA.StoreAutomation.BaseClass;
import SA.StoreAutomation.DataBinding;


public class CheckoutPage extends BaseClass {

	@FindBy(xpath = "//div[@class='table-responsive']//table//tr//td")
	public List<WebElement> cellCount;

	@FindBy(xpath = "//div[@class='table-responsive']//table//tr")
	public List<WebElement> rowCount;

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	ProductPage pro = new ProductPage();

	public String displayData(DataBinding data) {
        return data.getPrice();
    }

	@Test
	public void verfiyTable() {
		pro.productVerification();
	
		

		String Value1=DataBinding.getPrice();
	//	String Value1 = displayData(data);
		System.out.println(Value1);

		List<WebElement> rows = driver.findElements(By.xpath("(//div[@class='table-responsive']//table//tr)"));
		int rowcount = rows.size();
		for (int i = 1; i <= rowcount; i++) {
			List<WebElement> cells = driver
					.findElements(By.xpath("(//div[@class='table-responsive']//table//tr)[" + i + "]//td"));
			int cellCount = cells.size();
			for (int j = 2; j < cellCount; j++) {
				String unitPrice = driver
						.findElement(
								By.xpath("(//div[@class='table-responsive']//table//tr)[" + i + "]//td[" + j + "]"))
						.getText();
				if (unitPrice.equals(Value1)) {
					System.out.println("The values are equal");
					break;
				} else {
					System.out.println("We didn't find the exact match");
				}
			}
		}
	}
}
