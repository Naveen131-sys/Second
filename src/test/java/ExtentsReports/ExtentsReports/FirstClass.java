package ExtentsReports.ExtentsReports;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.HTMLReader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class FirstClass {

	public WebDriver driver;
	public ExtentSparkReporter a;
	public ExtentReports b;
	public ExtentTest test;
	
	@BeforeTest
	public void setTest() {
		
		a=new ExtentSparkReporter("C:\\Users\\NAVEEN\\eclipse-workspace\\ExtentsReports\\Reportssssss\\First.html");
		
		a.config().setDocumentTitle("My First Extents Report");
		a.config().setReportName("My First Functional Report");
		a.config().setTheme(Theme.DARK);
		b=new ExtentReports();
		b.attachReporter(a);
		b.setSystemInfo("Hostname", "LocalHost");
		b.setSystemInfo("OS", "Windows 10");
		
	}
	@AfterTest
	public void EndReport() {
		b.flush();
	}
	
	@BeforeMethod
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "F:\\New Folder\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.freecrm.com");
	}
	
	@Test
	public void TitleTest() {
		
		test=b.createTest("Facebook Title Validation");
		test.createNode("First Facebook Title Validation");
		String title=driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"#1 Free CRM customer relationship management software cloud");
		
		//test.createNode("Second Facebook Title Validation");
		//Assert.assertEquals("aaaa","aaaa");
	}
	
	
	@Test
	public void TitleTest1() {
		
		test=b.createTest("Random Validation");
		String title=driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("aaaa","aaaa");
	}
	
	@AfterMethod
	public void teardown(ITestResult Result) throws IOException {
		
		
		if(Result.getStatus()==ITestResult.FAILURE) {
			
			test.log(Status.FAIL, "The Failed Test Case is"+Result.getName());
			test.log(Status.FAIL, "The Failed Test Case Reason is"+Result.getThrowable());
			
			String abc=FirstClass.getscreenshot(driver, Result.getName());
			test.addScreenCaptureFromPath(abc);
		}
		
if(Result.getStatus()==ITestResult.SUCCESS) {
			
			test.log(Status.PASS, "The Passed Test Case is"+Result.getName());
			//test.log(Status.PASS, "The Failed Test Case Reason is"+Result.getThrowable());
		}

if(Result.getStatus()==ITestResult.SKIP) {
	
	test.log(Status.SKIP, "The Skipped Test Case is"+Result.getName());
	//test.log(Status.PASS, "The Failed Test Case Reason is"+Result.getThrowable());
}
	}

	
	


	
public static String getscreenshot(WebDriver driver,String screenshotname) throws IOException {
	
	String datetime=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
	
	TakesScreenshot ts= (TakesScreenshot) driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	String destination="C:\\Users\\NAVEEN\\eclipse-workspace\\ExtentsReports\\Reportssssss\\"+screenshotname+datetime+".png";
	
	File dest=new File(destination);
FileUtils.copyFile(source, dest);
	return datetime;
	
	
	

}

}
