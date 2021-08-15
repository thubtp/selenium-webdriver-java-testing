package automationfc.com;
 
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
public class Topic06_Textbox_TextArea_Exercise {
	WebDriver driver;
	String EmailAddress = "NghiemCan" + generateEmail();
	String currentUrl, userID, passWord, cusID;
	
	By CusName = By.xpath("//input[@name='name']");
	By DoB = By.name("dob");
	By Add = By.name("addr");
	By City = By.name("city");
	By State = By.name ("state");
	By Pin = By.name("pinno");
	By Telephone = By.name("telephoneno");
	By Email = By.name("emailid");
	By Password = By.name("password");
	
	//data test cho man hinh input
	String name,dob,add,city,state,pin,telephone;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//thoi gian cho xxxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://demo.guru99.com/v4/");
		name = "John";
		dob = "1987-10-10";
		add = "123 PO boxing";
		city = "Califoxnia";
		state = "Hawai";
		pin = "123456";
		telephone = "0987123456";
	
		
	}
	//link bai tap: https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit#
	
	
	@Test
	public void TC_01_Register() {
		currentUrl = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(EmailAddress);
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	@Test
	public void TC_02_Login() {
		driver.get(currentUrl);
		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys(userID);
		System.out.println(userID);
		
		driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys(passWord);
		System.out.println(passWord);
		driver.findElement(By.xpath("//input[@type ='submit']")).click();
		driver.findElement(By.xpath("//marquee[@class = 'heading3']")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class = 'heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		
		
	}
	
	@Test
	public void TC_03_Create_New_User() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(CusName).sendKeys(name);
		driver.findElement(DoB).sendKeys(dob);
		driver.findElement(Add).sendKeys(add);
		driver.findElement(City).sendKeys(city);
		driver.findElement(State).sendKeys(state);
		driver.findElement(Pin).sendKeys(pin);
		driver.findElement(Telephone).sendKeys(telephone);
		driver.findElement(Email).sendKeys(EmailAddress);
		driver.findElement(Password).sendKeys(passWord);
	
		
		driver.findElement(By.name("sub")).click();
		//verify thong tin sau khi hien thi
		cusID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(cusID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),add);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),telephone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),EmailAddress);
		SleepInSecond(3);
		
	
	}
	@Test
	public void TC_04_Edit_User() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(cusID);
		System.out.println(cusID);
		driver.findElement(By.name("AccSubmit")).click();
		//Kiem tra cac thong tin hien thi tren man hinh edit dung
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"),dob);
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"),add);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"),telephone);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"),EmailAddress);
	
		Assert.assertTrue(driver.findElement(By.name("sub")).isEnabled());
		Assert.assertTrue(driver.findElement(By.name("res")).isEnabled());
	
		
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
	
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	
}