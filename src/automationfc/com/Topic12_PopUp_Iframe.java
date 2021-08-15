package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic12_PopUp_Iframe {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor)driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	//@Test
public void TC_01_Fixed_Popup() {
	driver.get("https://ngoaingu24h.vn/");
	driver.findElement(By.cssSelector("button.login_.icon-before")).click();
	Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1 div.modal-content")).isDisplayed());
	
	driver.findElement(By.cssSelector("input#account-input")).sendKeys("Automationfc");
	driver.findElement(By.cssSelector("input#password-input")).sendKeys("Automationfc");
	driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1.buttonLoading")).click();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.row.error-login-panel")).getText(),"Tài khoản không tồn tại!");
	SleepInSecond(2);
	driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
	Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1 div.modal-content")).isDisplayed());
	
}
	//@Test
public void TC_02_RanDom_Popup_InDom() {
	driver.get("https://blog.testproject.io/");
	SleepInSecond(30);
	WebElement Popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
	
	if(Popup.isDisplayed()) {
		driver.findElement(By.cssSelector("div#close-mailch")).click();
		System.out.println("Popup is Displayed!");
	}
	else {
		System.out.println("Popup is not Displayed!");
	}
	driver.findElement(By.cssSelector("aside#secondary input.search-field")).sendKeys("Selenium");
	SleepInSecond(3);
	//driver.findElement(By.cssSelector("span.glass")).click();
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.glass")));
	SleepInSecond(3);
	List<WebElement> List_Result = driver.findElements(By.cssSelector("h3.post-title a"));
	
	for (WebElement item :  List_Result) {
		item.getText().contains("Selenium");
	}

}
	//@Test
public void TC_03_RanDom_Popup_NotInDom() {
	driver.get("https://shopee.vn/");
	SleepInSecond(3);
	List<WebElement> Popup = driver.findElements(By.cssSelector("div.shopee-popup__container"));
	SleepInSecond(5);
	if(Popup.size() !=0 && Popup.get(0).isDisplayed()) {
		driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
		System.out.println("Popup is Displayed!");
	}
	else {
		System.out.println("Popup is not Displayed!");
	}
	driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("MacbookPro");
	SleepInSecond(3);
	driver.findElement(By.xpath("//button[@class='btn btn-solid-primary btn--s btn--inline']")).click();
	

}
	@Test
public void Iframe() {
	driver.get("https://kyna.vn/");
	SleepInSecond(3);
	//dùng index
	//driver.switchTo().frame(0);
	
	//Name or ID
	
	//Element
	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
	Assert.assertEquals(driver.findElement(By.cssSelector("div.lfloat div._1drq")).getText(),"168K lượt thích");
	
	//switch to parent
	driver.switchTo().defaultContent();
	
	//switch to chat 
	driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
	driver.findElement(By.xpath("//div[@class='meshim_widget_components_chatButton_ButtonBar button_bar']//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
	//nhap thong tin
	driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("automation");
	driver.findElement(By.xpath("//input[@class='input_phone mobile_error_icon meshim_widget_widgets_PhoneField ltr ng-scope ng-pristine ng-valid desktop']")).
	sendKeys("0986123456");
	select = new Select (driver.findElement(By.xpath("//select[@id = 'serviceSelect']")));
	select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
	driver.findElement(By.xpath("//textarea[@class='meshim_widget_widgets_TextArea ltr input input_textarea ng-pristine ng-invalid ng-invalid-required desktop']")).sendKeys("hỗ trợ kỹ thuật");
	driver.findElement(By.xpath("//input[@class='submit meshim_widget_widgets_ConnAwareSubmit ltr wide valid ng-scope desktop']")).click();
	//Kiểm tra thông tin hiện ra 
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='field profile_field']")).isDisplayed());
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
//driver.quit();
}
 
}