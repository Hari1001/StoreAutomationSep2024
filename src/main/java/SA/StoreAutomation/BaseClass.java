package SA.StoreAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	public static WebDriver driver = new ChromeDriver();
	public static Properties pro;

	
	@BeforeSuite
	public static void loadConfig() throws IOException {

		FileInputStream fi;
		try {
			fi = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			pro = new Properties();
			pro.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public static void browserConfiguration() throws InterruptedException, IOException {
		driver.get(pro.getProperty("url"));
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
