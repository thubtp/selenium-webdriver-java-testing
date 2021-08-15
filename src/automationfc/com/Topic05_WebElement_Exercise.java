package automationfc.com;
 
import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic05_WebElement_Exercise {
	WebDriver driver;
	String firstname, lastname, emailAddress, password, fullname;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//thoi gian cho xxxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Khởi tạo data test
		firstname = "Osama";
		lastname = "Bin Laden";
		fullname = firstname + " " + lastname;
		emailAddress= "osama" + generateEmail();
		password = "123456";
		
	}
	//link bai tap: https://docs.google.com/document/d/1UaYIlYZMJib3ThkE2KaEhn9J2saOc5kSIhRYrGyNZwo/edit#
	
		@Test
	public void TC_05_Create_New_Account() {
		//mo url
		driver.get("http://live.demoguru99.com/");
			
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class = 'button' and @title = 'Register']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		
		//cách 1: dùng hàm IsDisplay để kiểm tra
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p[contains(text(),'" + fullname+ "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p[contains(string(),'" + emailAddress+ "')]")).isDisplayed());
		//cách 2 : dùng hàm gettext và contains (fullname và email)
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
		System.out.print(contactInformation);
		Assert.assertTrue(contactInformation.contains(fullname));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		//logout
		
		driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}
 
	@Test
	public void TC_06_Login_Invalid_Email_Password() {
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
	driver.findElement(By.id("email")).sendKeys(emailAddress);
	driver.findElement(By.id("pass")).sendKeys(password);
	driver.findElement(By.xpath("//button[@title='Login']")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(),"MY DASHBOARD");
	driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
	System.out.print(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText());
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, "+ firstname +" "+ lastname+ "!");
	String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
	Assert.assertTrue (contactInformation.contains(firstname+" "+lastname));
	Assert.assertTrue (contactInformation.contains(emailAddress));
	
	}
	
	@Test
public void TC_03_Name() {

}
public String generateEmail() {
	Random rand = new Random();
	return rand.nextInt(9999) + "@mail.vn";
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
driver.quit();
}
 
}