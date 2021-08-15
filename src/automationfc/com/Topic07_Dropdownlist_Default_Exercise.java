package automationfc.com;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic07_Dropdownlist_Default_Exercise {
	WebDriver driver;
	Select select;
	String firstName, lastName,day, month,year, emailAddress,companyName,passWord;
	List <String> Expected_Month;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	firstName = "John";
	lastName = "Henry";
	day = "7";
	month = "April";
	year = "1987";
	emailAddress = "john" + generateEmail();
	companyName = "ABC company";
	passWord = "123456";
	Expected_Month = new ArrayList<String>(Arrays.asList("Month","January","February","March","April","May","June","July","August","September","October","November","December"));
	}
	//https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit#
	@Test
public void TC_03_Register() {
	driver.get("https://demo.nopcommerce.com/");
	driver.findElement(By.xpath("//a[@class= 'ico-register']")).click();
	//input firstname last name
	driver.findElement(By.id("FirstName")).sendKeys(firstName);
	driver.findElement(By.id("LastName")).sendKeys(lastName);


	//chon day trong DDL Day
	select = new Select (driver.findElement(By.name("DateOfBirthDay")));
	select.selectByVisibleText(day);
	Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
	Assert.assertEquals(select.getOptions().size(),32);
	System.out.println(select.getOptions().size());
	
	//chon Month trong DDL month
	select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
	select.selectByVisibleText(month);
	Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
	Assert.assertEquals(select.getOptions().size(),13);
	System.out.println(select.getOptions().size());
	
	//chon year trong DDL
	select = new Select (driver.findElement(By.name("DateOfBirthYear")));
	select.selectByVisibleText(year);
	Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
	Assert.assertEquals(select.getOptions().size(),112);
	
	System.out.println(select.getOptions().size());
	
	//input thông tin email/company name/ password
	driver.findElement(By.id("Email")).sendKeys(emailAddress);
	driver.findElement(By.id("Company")).sendKeys(companyName);
	driver.findElement(By.id("Password")).sendKeys(passWord);
	driver.findElement(By.id("ConfirmPassword")).sendKeys(passWord);
	clickByJs(By.id("register-button"));
	
	
	//Verify vào trang home page sau khi đk thành công
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
	
	//Click vào MyAccount để kiểm tra ngày tháng nhập vào là đúng
	driver.findElement(By.xpath("//a[@class= 'ico-account']")).click();
	//Kiểm tra các thông tin đã nhập là đúng
	
	driver.findElement(By.id("FirstName")).getAttribute("value");
	Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
	Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
	
	select = new Select (driver.findElement(By.name ("DateOfBirthDay")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
	
	select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
	
	select = new Select (driver.findElement(By.name("DateOfBirthYear")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
	
	Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
	Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);
	SleepInSecond(3);
}
 
	@Test
public void TC_02_() {
	driver.get("https://demo.nopcommerce.com/");
		
	driver.findElement(By.xpath("//a[@class= 'ico-register']")).click();
	
	select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
	List <WebElement> allItems = select.getOptions();
	List<String> allItemsText = new ArrayList<String>();
	
	for (WebElement item: allItems) {
		System.out.println(item.getText());
		allItemsText.add(item.getText());
	}
	Assert.assertEquals(allItemsText,Expected_Month);
			
}
	@Test
public void TC_03_Name() {

}
public void SleepInSecond (long timeoutInSecond){
	try {
		Thread.sleep(timeoutInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public String generateEmail() {
	Random rand = new Random();
	return rand.nextInt(9999) + "@mail.vn";
}
	
public void clickByJs(By by){
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
}

//@AfterClass
public void afterClass() {
driver.quit();
}
 
}