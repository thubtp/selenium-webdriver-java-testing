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
public class Topic05_WebElement_Exercise2 {
	WebDriver driver;
	String firstname, lastname, emailAddress, password, fullname;
	By emailtextbox = By.id("mail");
	By EducationTextArea= By.id("edu");
	By RadioUnder18= By.id("under_18");
	By javaCheckbox= By.id("java");
	
	By Passwordtextbox= By.id("password");
	By DisableCheckbox= By.id("radio-disabled");
	By DisableBio= By.id("bio");
	By DisableJob3= By.id("job3");
	By DisableSlider_2= By.id("slider-2");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//thoi gian cho xxxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	//link bai tap: https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit#
	
	//@Test
	//cach viet cho newbie	
	public void TC_01_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//hàm kiểm tra đk 
		//Nếu đúng thì mới vào trong hàm IF
		//sai thì không vào
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("emailtest@gmail.com");
			System.out.print("Mail textbox is display");
			
		}
		else { System.out.print("Mail textbox is not  display");
		}
		if(driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).click();
			System.out.print("Radio button Under18 is display");
			
		}
		else { System.out.print("Radio button Under18 is not  display");
		}
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("input to education textarea");
			System.out.print("TextArea Education is display");
			
		}
		else { System.out.print("TextArea Educations is not  display");
		}
	}
		//if-else : đúng vào if - sai vào else
	//cach viet cho nguoi co kn
	//@Test
	public void TC_01_isDisplay_2() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//hàm kiểm tra đk 
		//Nếu đúng thì mới vào trong hàm IF
		//sai thì không vào
		By emailtextbox = By.id("mail");
		By EducationTextArea= By.id("edu");
		By RadioUnder18= By.id("under_18");
		if(isElementDisplay(emailtextbox)) {
			sendkeyToElement(emailtextbox,"emailtest@gmail.com");
		}
		if(isElementDisplay(RadioUnder18)) {
			clicktoElement(RadioUnder18);		
		}
		if(isElementDisplay(EducationTextArea)) {
			sendkeyToElement(EducationTextArea,"input to education textarea");
		}
	}
	//@Test
	public void TC_02_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Kiểm tra các phần tử dưới Enable
		Assert.assertTrue(isElementEnabled(emailtextbox));
		Assert.assertTrue(isElementEnabled(EducationTextArea));
		Assert.assertTrue(isElementEnabled(RadioUnder18));
		Assert.assertTrue(isElementEnabled(javaCheckbox));
		
		//Kiểm tra các phần tử dưới Disable
		Assert.assertFalse(isElementEnabled(Passwordtextbox));
		Assert.assertFalse(isElementEnabled(DisableCheckbox));
		Assert.assertFalse(isElementEnabled(DisableBio));
		Assert.assertFalse(isElementEnabled( DisableJob3));
		Assert.assertFalse(isElementEnabled( DisableSlider_2));
		
			}
	
	//@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clicktoElement(RadioUnder18);
		clicktoElement(javaCheckbox);
		Assert.assertTrue(isElementSelected(RadioUnder18));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
		clicktoElement(javaCheckbox);
		Assert.assertFalse(isElementSelected(javaCheckbox));
		
	}
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		By PassWord = By.id("new_password");
		By SignUpButton = By.id("create-account");
		By newRegisterCheckbox= By.id("marketing_newsletter");
		
		By upperCaseCompleted = By.xpath("//ul[@class='small-meta selfclear']/li[@class='uppercase-char completed']");
		By lowerCaseCompleted= By.xpath("//ul[@class='small-meta selfclear']/li[@class='lowercase-char completed']");
		By OneNumberCompleted = By.xpath("//ul[@class='small-meta selfclear']/li[@class='number-char completed']");
		By OneSpecialCompleted = By.xpath("//ul[@class='small-meta selfclear']/li[@class='special-char completed']");
		By GreaterThan8CharacterCompleted = By.xpath("//ul[@class='small-meta selfclear']/li[@class='8-char completed']");
		
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");;
		driver.findElement(By.id("new_username")).sendKeys("thubtptestAT");;
		
		
		//UperCase
		driver.findElement(PassWord).sendKeys("A");
		Assert.assertTrue(isElementDisplay(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(SignUpButton));
		driver.findElement(PassWord).clear();
		
		//LowerCase
		driver.findElement(PassWord).sendKeys("a");
		Assert.assertTrue(isElementDisplay(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(SignUpButton));
		driver.findElement(PassWord).clear();
		
		//OneNumber
		driver.findElement(PassWord).sendKeys("123456");
		Assert.assertTrue(isElementDisplay(OneNumberCompleted));
		Assert.assertFalse(isElementEnabled(SignUpButton));
		driver.findElement(PassWord).clear();
		
		//OneSpecial Character
		driver.findElement(PassWord).sendKeys("@");
		Assert.assertTrue(isElementDisplay(OneSpecialCompleted));
		Assert.assertFalse(isElementEnabled(SignUpButton));
		driver.findElement(PassWord).clear();
		
		//Greater than 8 character
		
		driver.findElement(PassWord).sendKeys("thubtptest");
		Assert.assertTrue(isElementDisplay(GreaterThan8CharacterCompleted));
		Assert.assertFalse(isElementEnabled(SignUpButton));
		driver.findElement(PassWord).clear();
		
		
		// thỏa mãn tất cả các điều kiện
		
		driver.findElement(PassWord).sendKeys("Aa1@testing");
		Assert.assertFalse(isElementDisplay(upperCaseCompleted));
		Assert.assertFalse(isElementDisplay(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplay(OneNumberCompleted));
		Assert.assertFalse(isElementDisplay(OneSpecialCompleted));
		Assert.assertFalse(isElementDisplay(GreaterThan8CharacterCompleted));
	
		Assert.assertTrue(isElementEnabled(SignUpButton));
		
		clicktoElement(newRegisterCheckbox);
	
		Assert.assertTrue(isElementSelected(newRegisterCheckbox));
		
	}
	

	public void SleepInSecond (long timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isElementDisplay(By by) {
		if(driver.findElement(by).isDisplayed()){
			System.out.println(by+ "isDisplay");
			return true;
		}
		else {
			System.out.println(by+ "isn'tDisplay");
		return false;
		}
	}
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()){
			System.out.println(by+ "is Enabled");
			return true;
		}
		else {
			System.out.println(by+ "isn't Enabled");
		return false;
		}
	}
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()){
			System.out.println(by+ "is Selected");
			return true;
		}
		else {
			System.out.println(by+ "isn't Selected");
		return false;
		}
	}
	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		
	}
	public void clicktoElement(By by) {
		driver.findElement(by).click();
	}
	
	
	
}