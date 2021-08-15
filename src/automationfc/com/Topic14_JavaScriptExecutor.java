package automationfc.com;
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

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
public class Topic14_JavaScriptExecutor {
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
public void TC_01_JSExecutor() {
	//step1:
	driver.get("http://live.demoguru99.com/");
	//step 2: get domain của page
	String GuruDomain =(String) executeForBrowser("return document.domain");
	Assert.assertEquals(GuruDomain,"live.demoguru99.com");
	//step 3: get URL của page
	String GuruURL= (String) executeForBrowser("return document.URL");
	Assert.assertEquals(GuruURL,"http://live.demoguru99.com/");
	
	//step4: Open Mobile
	highlightElement ("//a[text()='Mobile']");
	SleepInSecond(3);
	clickToElementByJS("//a[text()='Mobile']");
	SleepInSecond(3);
	//step5 & 6 : Add Samsung Galaxy to Cart then verify message
	clickToElementByJS("//a[@title='Samsung Galaxy']//following-sibling::div//button");
	SleepInSecond(2);
	Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	SleepInSecond(2);
	//Step 7: click to Customer service
	scrollToElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	SleepInSecond(2);
	String CustomerServiceTitle = (String)executeForBrowser("return document.title");
	
	//Step 8: scroll tới cuối page
	scrollToBottomPage();
	//Step 9: Input email hợp lệ 
	sendkeyToElementByJS("//input[@id = 'newsletter']","thubtp@gmail.com");
	
	//Step10&11 : click to Subscribe then verify message
	clickToElementByJS("//button[@title = 'Subscribe']");
	SleepInSecond(3);
	Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
	
	//Step 12: 
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	String DemoGuru = (String)executeForBrowser ("return document.domain");
	Assert.assertEquals(DemoGuru,"demo.guru99.com");
}
	
@Test
public void TC_02_VerifyHTML5() {
	driver.get("https://automationfc.github.io/html5/index.html");
	clickToElementByJS("//input[@name='submit-btn']");
	SleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"),"Please fill out this field.");

	
	//fill giá trị vào Name rồi submit lại
	sendkeyToElementByJS("//input[@id='fname']","Thu");
	clickToElementByJS("//input[@name='submit-btn']");
	Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"),"Please fill out this field.");
	
	//fill giá trị vào Password rồi submit lại
	sendkeyToElementByJS("//input[@id='pass']","Thu");
	clickToElementByJS("//input[@name='submit-btn']");
	Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please fill out this field.");
	
	//fill giá trị vào email
	sendkeyToElementByJS("//input[@id='em']","Thu");
	clickToElementByJS("//input[@name='submit-btn']");
	SleepInSecond(2);
	Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please enter an email address.");
	
	sendkeyToElementByJS("//input[@id='em']","Thu@gm");
	clickToElementByJS("//input[@name='submit-btn']");
	SleepInSecond(2);
	Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please match the requested format.");
	
	sendkeyToElementByJS("//input[@id='em']","thubtp@gmail.com");
	clickToElementByJS("//input[@name='submit-btn']");
	SleepInSecond(2);
	Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please select an item in the list.");
		
}
@Test
public void TC_03_VerifyHTML5() {
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
 
public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean isExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void highlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
	SleepInSecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElement(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
	if (status) {
		return true;
	} else {
		return false;
	}
}
public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}
}