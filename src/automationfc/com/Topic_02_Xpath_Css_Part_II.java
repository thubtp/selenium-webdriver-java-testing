package automationfc.com;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class Topic_02_Xpath_Css_Part_II {
	//Biến Driver đại diện cho Selenium Webdriver
WebDriver driver;
@BeforeClass
public void beforeClass() {
	//Open Firefox
	driver = new FirefoxDriver();
	//time to open browser
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	
}
 //link bai tap https://docs.google.com/document/d/1UaYIlYZMJib3ThkE2KaEhn9J2saOc5kSIhRYrGyNZwo/edit#
@Test
public void TC_01_Login_EmptyEmail_Password() {
	
	//open  AUT/SUT
	driver.get("http://live.demoguru99.com/");
	//open My Account Link
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	SleepInSecond(3);
	//keep email and password blank
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys();
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys();
	SleepInSecond(3);
	//click buttton Login
	driver.findElement(By.id("send2")).click();
	//compare error msg on screen
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
		
}

@Test
public void TC_02_Login_InvalidEmail() {
	
	//open  AUT/SUT
	driver.get("http://live.demoguru99.com/");
	//open My Account Link
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	SleepInSecond(3);
	//keep email and password blank
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@dsfsdfd");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
	SleepInSecond(3);
	//click buttton Login
	driver.findElement(By.id("send2")).click();
	
	//compare error msg on screen
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	
}
@Test
public void TC_03_Login_InvalidPassword() {
	
	//open  AUT/SUT
	driver.get("http://live.demoguru99.com/");
	//open My Account Link
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	SleepInSecond(3);
	//keep email and password blank
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thubtp@gmail.com");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
	SleepInSecond(3);
	//click buttton Login
	driver.findElement(By.id("send2")).click();
	
	//compare error msg on screen
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	
}
@Test
public void TC_04_Login_IncorrectPassword() {
	
	//open  AUT/SUT
	driver.get("http://live.demoguru99.com/");
	//open My Account Link
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	SleepInSecond(3);
	//keep email and password blank
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thubtp@gmail.com");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
	SleepInSecond(3);
	//click buttton Login
	driver.findElement(By.id("send2")).click();
	
	//compare error msg on screen
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

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