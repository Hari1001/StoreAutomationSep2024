package SA.StoreAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
	public static WebDriver driver = new ChromeDriver();
	public static Properties pro;
	static ExtentTest test;
	static ExtentReports report;

//	@BeforeClass
//	public static void startTest()
//	{
//	report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
//	test = report.startTest("ExtentDemo");
//	}
	
	@BeforeSuite
	public static void loadConfig() throws IOException {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		FileInputStream fi;
		try {
			fi = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			pro = new Properties();
			pro.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
//	static ExtentTest test;
//	static ExtentReports report;
//	@BeforeClass
//	public static void startTest()
//	{
//	report = new ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html");
//	test = report.startTest("ExtentDemo");
//	}
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
