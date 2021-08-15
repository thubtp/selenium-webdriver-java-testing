package automationfc.com;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class Topic_02_Xpath_Css_Part_I_Locator {
	//Biến Driver đại diện cho Selenium Webdriver
WebDriver driver;
@BeforeClass
public void beforeClass() {
	//Open Firefox
	driver = new FirefoxDriver();
	//time to open browser
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	//Mo trang  AUT/SUT
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
}
 
@Test
public void TC_01_ID() {
	//input value on Firstname text box
	driver.findElement(By.id("FirstName")).sendKeys("Automation");
	SleepInSecond(3);
	//click on gender male
	driver.findElement(By.id("gender-male")).click();
	
	
}
 
@Test
public void TC_02_Class() {
	driver.navigate().refresh();
	driver.findElement(By.className("search-box-text")).sendKeys("Macboook");
	driver.findElement(By.className("search-box-button")).click();
}
 
@Test
public void TC_03_Name() {
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	SleepInSecond(3);
	driver.findElement(By.name("Email")).sendKeys("thubtp@gmail.com");
	SleepInSecond(3);
	driver.findElement(By.name("Newsletter")).click();
	SleepInSecond(3);
	
}
@Test
public void TC_04_Tagname() {
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	SleepInSecond(3);
	System.out.println("Sum input="+ driver.findElements(By.tagName("input")).size());
	System.out.println("Sum Link="+ driver.findElements(By.tagName("a")).size());
	SleepInSecond(3);
}
@Test
public void TC_05_LinkText() {
	driver.findElement(By.linkText("Log in")).click();
	SleepInSecond(3);

}
@Test
public void TC_06_PartialLinkText() {
	
	driver.findElement(By.partialLinkText("Recently viewed products")).click();
	SleepInSecond(3);
	driver.findElement(By.partialLinkText("New product")).click();
	SleepInSecond(3);

}
@Test
public void TC_07_Css() {
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	SleepInSecond(3);
	driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Test");
	SleepInSecond(3);
	driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Thubtp@gmail.com");
	SleepInSecond(3);
	driver.findElement(By.cssSelector("a[href*='login']")).click();
	
}
@Test
public void TC_08_Xpath() {
	driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	driver.findElement(By.xpath("//*[@id='gender-male']")).sendKeys("Automation FC");
	SleepInSecond(3);
	driver.findElement(By.xpath("//input[contains(@class,'search-box-text')]")).sendKeys("Macbook");
	SleepInSecond(3);
	driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("thubtp@gmail.com");
	SleepInSecond(3);
	driver.findElement(By.xpath("//a[contains(text(),'Recently viewed products')]")).click();
}

@AfterClass
public void afterClass() {
driver.quit();
}
public void SleepInSecond (long timeoutInSecond){
	try {
		Thread.sleep(timeoutInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}