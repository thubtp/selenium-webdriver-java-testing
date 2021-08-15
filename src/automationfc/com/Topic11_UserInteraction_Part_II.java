package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic11_UserInteraction_Part_II {
	WebDriver driver;
	Actions action ;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
//	System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//	driver = new FirefoxDriver();
	driver = new ChromeDriver();
	action = new Actions(driver);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	//@Test
public void TC_04_Right_Click() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	
	action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	SleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	
	action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	SleepInSecond(1);
	driver.switchTo().alert().accept();
}
	@Test
public void TC_05_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		SleepInSecond(2);
		WebElement SmallCircle = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		WebElement BigCircle =driver.findElement(By.xpath("//div[@id = 'droptarget']"));
		
		action.dragAndDrop(SmallCircle, BigCircle).perform();
		SleepInSecond(4);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id = 'droptarget']")).getText(),"You did great!");
		
		BigCircle.getCssValue("background-color");
		System.out.println(BigCircle.getCssValue("background-color"));
		System.out.println(Color.fromString(BigCircle.getCssValue("background-color")).asHex());
		Assert.assertEquals(Color.fromString(BigCircle.getCssValue("background-color")).asHex(),"#03a9f4");
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