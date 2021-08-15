package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic15_UploadFile_PartI{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String filename1 = "image1.png";
	String filename2 = "image2.png";	
	String filename3 = "image3.png";
	String FileUpload1 = projectPath  +"\\FileUpload"+"\\"+ filename1;
	String FileUpload2 = projectPath  +"\\FileUpload"+"\\"+ filename2;
	String FileUpload3 = projectPath  +"\\FileUpload"+"\\"+ filename3;
	@BeforeClass
	public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor)driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	@Test
public void TC_01_Window() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	SleepInSecond(2);
	driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(FileUpload1);
	driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(FileUpload2);
	driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(FileUpload3);
	
	List<WebElement> allButton = driver.findElements(By.xpath("//table[@class='table table-striped']//button[@class=\"btn btn-primary start\"]"));
	for (WebElement button : allButton){
		button.click();
	}
	SleepInSecond(3);
	//verify các file đã được upload
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='image1.png']")).isDisplayed());
//	Assert.assertTrue(driver.findElement(By.xpath("//a[@title='filename2']")).isDisplayed());
//	Assert.assertTrue(driver.findElement(By.xpath("//a[@title='filename3']")).isDisplayed());
}
public void SleepInSecond (long timeoutInSecond){
	try {
		Thread.sleep(timeoutInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@AfterClass
public void afterClass() {
//driver.quit();
}
 
}