package automationfc.com;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//link bai tap: https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit#heading=h.a6pyp263td3p
public class Topic08_Dropdownlist_Custom_Part1 {
	WebDriver driver;
	//wait
	WebDriverWait explicitWait;
	//inject 1 javascript code
	JavascriptExecutor jsExecutor;
	String projectPath= System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait (driver,15);
		//ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	//@Test
	public void TC_04_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id ='number-button']//span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","5");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-text') and text()='5']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-text')]")).getText(),"5");
		
		selectItemInCustomDropdown("//span[@id ='number-button']//span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","15");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-text')]")).getText(),"15");

	}
	//@Test
	public void TC_05_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']/div[@class='divider default text']","//div[@class='visible menu transition']//span","Jenny Hess");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text'and text()='Jenny Hess']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Jenny Hess");
		
		
	}
	//@Test
	public void TC_06_VueJS() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li","First Option");
		SleepInSecond(3);
	
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li","First Option");
		SleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		//Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(),"First Option");
		
		
		//selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li","Second Option");
		//SleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		//Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(),"Second Option");
		
	}
	
	//@Test
	//chay tren chrome
	public void TC_07_Angolar() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//span[@aria-owns='games_options']","//ul[@id='games_options']/li","American Football");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder ='Select a game' and @aria-label='American Football']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder ='Select a game']")).getAttribute("aria-label"),"American Football");
		selectItemInCustomDropdown("//span[@aria-owns='games_options']","//ul[@id='games_options']/li","Basketball");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder ='Select a game' and @aria-label='Basketball']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder ='Select a game']")).getAttribute("aria-label"),"Basketball");
		SleepInSecond(3);
		
		selectItemInCustomDropdown("//span[@aria-owns='customers_options']","//ul[@id='customers_options']/li","Anne Dodsworth");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@aria-placeholder='Select a name' and @aria-label='Anne Dodsworth']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-placeholder='Select a name']")).getAttribute("aria-label"),"Anne Dodsworth");
		
	}
	//@Test
	//chay tren firefox dung ham selectItemInCustomDropdown
	public void TC_08_EditableDDL_1() {
		driver.get("https://valor-software.com/ng2-select/");
		selectItemInCustomDropdown("//tab[@class='active tab-pane']//i[@class='caret pull-right']","//tab[@class='active tab-pane']//a[@class='dropdown-item']/div","Amsterdam");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//tab[@class='active tab-pane']//ng-select//following-sibling::pre")).getText(),"Amsterdam");
		
		selectItemInCustomDropdown("//tab[@class='active tab-pane']//i[@class='caret pull-right']","//tab[@class='active tab-pane']//a[@class='dropdown-item']/div","Berlin");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//tab[@class='active tab-pane']//ng-select//following-sibling::pre")).getText(),"Berlin");
	}
	
	//@Test
	//chay tren firefox dung ham senKeythenEnterInCustomDropdown
	public void TC_08_EditableDDL_2() {
		driver.get("https://valor-software.com/ng2-select/");
		senKeythenEnterInCustomDropdown("//tab[@class='active tab-pane']//i[@class='caret pull-right']","//tab[@class='active tab-pane']//input[contains(@class,'ui-select-search')]","//tab[@class='active tab-pane']//a[@class='dropdown-item']/div","Amsterdam");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//tab[@class='active tab-pane']//ng-select//following-sibling::pre")).getText(),"Amsterdam");
		
	}
	@Test
	//chạy tren chrome moi tab dc
	public void TC_09_TabToSelectDDL() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		TabToSelectCustomDropdown("//input[@class='search']","Aland Islands");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//tab[@class='active tab-pane']//ng-select//following-sibling::pre")).getText(),"Amsterdam");
		
	}
	//@Test
	public void TC_11_JqueryEditable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		senKeythenEnterInCustomDropdown("//div[@id='default-place']/input","//div[@id='default-place']/input","//ul[@class='es-list']/li","Citroen");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='Citroen']")).getAttribute("class"),"es-visible");
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		SleepInSecond(2);
		
		senKeythenEnterInCustomDropdown("//div[@id='default-place']/input","//div[@id='default-place']/input","//ul[@class='es-list']/li","Audi");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='Audi']")).getAttribute("class"),"es-visible");
		
	}
	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		List <WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				if(!item.isDisplayed()) {//Nếu item đó đang không phải là item cần chọn thì scroll
					//System.out.println("scroll");
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
					SleepInSecond(2);
				}
				item.click();
				break;
			}
		}
	}
	public void TabToSelectCustomDropdown(String TextboxXpath, String expectedItem) {
		//
		driver.findElement(By.xpath(TextboxXpath)).sendKeys(expectedItem);
		SleepInSecond(3);
		driver.findElement(By.xpath(TextboxXpath)).sendKeys(Keys.TAB);
		
		
	}
	public void senKeythenEnterInCustomDropdown(String parentXpath,String textbox, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		driver.findElement(By.xpath(textbox)).sendKeys(expectedItem);
			
		//chờ cho tất cả các item được load ra thành công -> child elements
		//lấy tất cả các element lưu vào l
		List <WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyệt qua tung item
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				if(!item.isDisplayed()) {//Nếu item đó đang không phải là item cần chọn thì scroll
					//System.out.println("scroll");
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
					SleepInSecond(2);
				}
				item.click();
				break;
					
			}
		}
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