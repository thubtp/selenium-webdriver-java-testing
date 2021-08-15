package automationfc.com;
 
import java.util.List;
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
@Test
public class Topic04_WebBrowser_Exercise {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
	//
		driver = new FirefoxDriver();
		//thoi gian cho xxxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}	
	//link bài tập https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit#
	
	public void TC_01_Verify_Url (){
		driver.get("http://live.demoguru99.com/");
		//truy cap vao link 
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title='My Account']")).click();
		//so sanh link voi yeu cau
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
	
		//click vào CREATE AN ACCOUNT
		driver.findElement(By.xpath("//a[@class='button' and @title = 'Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
}
 
	public void TC_02_Verify_Title() {
		driver.findElement(By.xpath("//img[@class='large']")).click();
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		driver.findElement(By.xpath("//a[@class='button' and @title = 'Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
}
	public void TC_03_Verify_Navigation() {
		driver.findElement(By.xpath("//img[@class='large']")).click();
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button' and @title = 'Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	
}
	public void TC_04_Verify_Page_Source() {
		driver.findElement(By.xpath("//img[@class='large']")).click();
		SleepInSecond(3);
	
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title='My Account']")).click();
		SleepInSecond(3);
		
		//khai báo và khởi tạo biến tại màn hình login
		String currentPageSource = driver.getPageSource();
		Assert.assertTrue(currentPageSource.contains("Login or Create an Account "));
		
		driver.findElement(By.xpath("//a[@class='button' and @title = 'Create an Account']")).click();
		SleepInSecond(3);
		//Khởi tạo lại giá trị mới tại màn hình Register
		currentPageSource = driver.getPageSource();
		Assert.assertTrue(currentPageSource.contains("Create an Account"));
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