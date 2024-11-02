package SA.StoreAutomation;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utils extends BaseClass {

	public static void clickOnElement(WebElement ele) {
		try {
			ele.click();
			implicitWait(2);
		} catch (Exception e) {
			System.out.println("Not able to click on element : " + e.getMessage());
		}
	}
	
	public static void sendKeysToElement(WebElement ele, String value) {
		try {
			ele.clear();
			ele.sendKeys(value);
			implicitWait(2);
		} catch (Exception e) {
			System.out.println("Not able to Enter the value : " + e.getMessage());
		}
	}

	public static void waitForElementVisible(WebElement ele, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			System.out.println("The element is not visible " + ele + " : " + e.getMessage());
		}
	}

	public static void implicitWait(int timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public static void mouseHover(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
	}

	public static void rightClick(WebElement ele) {
		Actions act = new Actions(driver);
		act.contextClick(ele).build().perform();
	}

	
	
	public static void doubleClick(WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele).build().perform();
	}

	public static String getText(WebElement ele) {
		return ele.getText();
	}

	public static void selectByValue(WebElement element, String value) {

		try {
			Select se = new Select(element);
			se.selectByVisibleText(value);
		} catch (Exception e) {
			Assert.assertTrue(false, "Couldnt able to select the index : " + element);
		}
	}

	public static void selectByIndex(WebElement element, int index) {
		try {
			Select se = new Select(element);
			se.selectByIndex(index);
		} catch (Exception e) {
			Assert.assertTrue(false, "Couldnt able to select the index : " + element);
		}
	}

	public static void scrollToElement(WebElement element) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			Assert.fail("Couldn't able to click through JSExecutor : " + e.getMessage() + " : " + element);

		}
	}

	public static void jsExecute(WebElement element) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Assert.fail("Couldn't able to click through JSExecutor : " + e.getMessage() + " : " + element);

		}
	}

	public static void KeySEnter(WebElement element) {

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		element.sendKeys(selectLinkOpeninNewTab);// 
	}

	public static void moveToWindow() {
		String parent = driver.getWindowHandle();
		System.out.println("Parent URL : " + driver.getCurrentUrl());
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			String child = it.next();
			if (!parent.equals(child)) {
				driver.switchTo().window(child);
				Utils.screenshot("Child");
				System.out.println(driver.getCurrentUrl());
				driver.close();
			}
		}
		driver.switchTo().window(parent);
	}

	public static void screenshot(String fileName) {
		try {
			File copy = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(copy, new File("D://Testing//StoreAutomation//Screenshots//" + fileName + ".jpg"));
		} catch (Exception e) {
			Assert.fail("Failing while taking screenshot" + e.getMessage());
		}
	}
}
