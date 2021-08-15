package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic10_Alert {
	WebDriver driver;
	JavascriptExecutor Jsexecutor;
	Alert alert;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	Jsexecutor = (JavascriptExecutor) driver;
	expliciWait = new WebDriverWait(driver,10);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	
	}
	//@Test
public void TC01_Accept_Alert() {
	//driver.get("http://demo.guru99.com/v4/index.php");
	//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	//cho cho Alert xuat hien + Switch qua Alert
	//alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	//SleepInSecond(5);
	//verify alert text
	//Assert.assertEquals(alert.getText(),"User or Password is not valid");
	//accept
	//alert.accept();
	
	
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	SleepInSecond(3);
	alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	Assert.assertEquals(alert.getText(),"I am a JS Alert");
	alert.accept();
	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");	
}

//@Test
public void TC02_Confirm_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");


	JSexecutorScroll("//button[text()='Click for JS Confirm']");
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	SleepInSecond(3);
	alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	Assert.assertEquals(alert.getText(),"I am a JS Confirm");
	alert.dismiss();
	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
}
//@Test
public void TC_03_Prompt_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");


	JSexecutorScroll("//button[text()='Click for JS Prompt']");
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

	alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	SleepInSecond(2);
	Assert.assertEquals(alert.getText(),"I am a JS prompt");
	
	alert.sendKeys("AutomationTesting");
	SleepInSecond(2);
	alert.accept();
	
	Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: AutomationTesting");
}
//@Test
public void TC_04_Authentication_Alert() {
	driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
}
@Test
public void TC_05_Authentication_Alert() {
	driver.get("http://the-internet.herokuapp.com/");
	SleepInSecond(3);
	String href = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	System.out.println("url" + href);
	String Url = HrefToUrl(href,"admin","admin");
	System.out.println("url" + Url);
	SleepInSecond(3);
	driver.get (href);
	//Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	

}
public String HrefToUrl(String href, String username, String password) {
	String [] hrefValue = href.split("//");
	String Url = hrefValue[0]+username+":"+password+"@"+hrefValue[1];
	return Url;
}
public void SleepInSecond (long timeoutInSecond){
	try {
		Thread.sleep(timeoutInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void JSexecutorScroll(String by ) {
	WebElement item= expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(by)));
	//System.out.println(item.getText());
	Jsexecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(by)));
}

//@AfterClass
public void afterClass() {
driver.quit();
}
 
}