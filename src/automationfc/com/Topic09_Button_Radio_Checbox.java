package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic09_Button_Radio_Checbox {
	WebDriver driver;
	boolean status; 
	JavascriptExecutor JsExecutor;

	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
	
	JsExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	//@Test
public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		//verify button đang disable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("button status: " + status);
		Assert.assertFalse(status);
		
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0987123456");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		//verify button đang enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("button status: " + status);
		Assert.assertTrue(status);
		
		//refresh trang
		driver.navigate().refresh();
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		JsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(By.cssSelector(".fhs-btn-login")));
		
		SleepInSecond(5);
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		
		//khong can click vao tab "Dang Nhap"
		driver.navigate().refresh();
		JsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(By.cssSelector(".fhs-btn-login")));
		SleepInSecond(3);
		JsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector(".fhs-btn-login")));
		
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		
}
 
	//@Test
public void TC_02_DefaultCheckBox_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		checkToItem(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).click();
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		
		driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).click();
		SleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		SleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
		
}

//	@Test
public void TC_03_Checkbox_SelectedAll() {
	
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> Checkboxes = driver.findElements(By.xpath("//span[@class = 'form-checkbox-item']//input"));
		
		for (WebElement checkbox : Checkboxes) {
			if(!checkbox.isSelected()) {
			checkbox.click();
			SleepInSecond(1);
			}
		}
		for (WebElement item : Checkboxes) {
			Assert.assertTrue (item.isSelected());
		}
}
	@Test
public void TC_04_CustomerCheckboxAndRadioButon(){
	//1.driver.get("https://material.angular.io/components/radio/examples");
		
	//the span click + khong verify dc
	//WebElement checkbox = driver.findElement(By.xpath("//input[@value = 'Spring']/preceding-sibling::span[@class='mat-radio-outer-circle']"));
	//checkbox.click();
	
	//The Input khong click dc + verify duoc
	//Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Spring']")).isSelected());
	
	//Dung Javascript de click va verify
	//WebElement checkbox = driver.findElement(By.xpath("//input[@value = 'Spring']"));
	//JsExecutorClick(checkbox);
	//Assert.assertTrue(checkbox.isSelected());	
	
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	//truoc khi click  thi gia tri checkbox = false
	//Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']//div[contains(@class,'appsMaterialWizToggleRadiogroupOffRadio')]")).isDisplayed());
	//driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']//div[contains(@class,'appsMaterialWizToggleRadiogroupOffRadio')]")).click();
	//	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']//div[contains(@class,'appsMaterialWizToggleRadiogroupOffRadio')]")).isDisplayed());
	
	
	SleepInSecond(2);
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Ngãi' and @aria-checked='false']//div[contains(@class,'quantumWizTogglePapercheckboxInnerBox')]")).isDisplayed());
	SleepInSecond(2);
	driver.findElement(By.xpath("//div[@aria-label='Quảng Ngãi']//div[contains(@class,'exportInnerBox')]")).click();
	SleepInSecond(2);
	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Ngãi' and @aria-checked='true']//div[contains(@class,'quantumWizTogglePapercheckboxCheckMarkContainer')]")).isDisplayed());
}


public void checkToItem(By by) {
		WebElement checkbox = driver.findElement(by);
		if(checkbox.isSelected()){
			checkbox.click();
		}
}
public void JsExecutorClick(WebElement by) {
	JsExecutor.executeScript("arguments[0].click();", by);
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