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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic11_UserInteraction_Part_I {
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
public void TC_01_Hover_Mouse_1() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	action.moveToElement(driver.findElement(By.xpath("//input[@id ='age']"))).perform();
	SleepInSecond(3);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),"We ask for your age only for statistical purposes.");
}
	//@Test
public void TC_01_Hover_Mouse_2() {
	driver.get("https://www.myntra.com/");
	action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
	SleepInSecond(2);
	driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Kids Accessories']")).click();
	SleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Accessories']")).isDisplayed());
	}
	//@Test
public void TC_01_Hover_Mouse_3() {
		driver.get("https://hn.telio.vn/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[@class = 'menu-link' and @href='/do-an-vat?source=home']//span[text()='Đồ ăn vặt']"))).perform();
		
		SleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[text()='Bắp rang bơ']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[text()='Đồ ăn vặt']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[text()='Ô mai']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[text()='Hoa quả sấy']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//a[text()='Snack']")).isDisplayed());
		
	}	
	//@Test
public void TC_02_Click_And_Hold_SelectMultipleItem() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		SleepInSecond(2);
		//Lay ra toan bo cac item
		List <WebElement> AllNumberItem = driver.findElements(By.xpath("//ol[@id ='selectable']/li"));
		//click and hold de chon 3 item dau tien
		action.clickAndHold(AllNumberItem.get(0)).perform();
		action.moveToElement(AllNumberItem.get(2)).perform();
		SleepInSecond(1);
		action.release().perform();
		
}
	//@Test
public void TC_02_Click_And_Hold_SelectedItem() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		SleepInSecond(2);
		List <WebElement> AllNumberItem = driver.findElements(By.xpath("//ol[@id ='selectable']/li"));
		//click va chọn 1 số số 
		action.keyDown(Keys.CONTROL).perform();
		action.click(AllNumberItem.get(0))
		.click(AllNumberItem.get(4))
		.click(AllNumberItem.get(9)).perform();
		action.keyUp(Keys.CONTROL).release().perform();
		
		Assert.assertEquals(driver.findElements(By.xpath("//ol[@id ='selectable']/li[@class='ui-state-default ui-selectee ui-selected']")).size(),3);
}
	@Test
public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		SleepInSecond(2);
		action.moveToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		SleepInSecond(1);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		action.release().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
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
 
}