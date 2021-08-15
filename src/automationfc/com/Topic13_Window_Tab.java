package automationfc.com;
 
import java.util.List;
import java.util.Set;
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
public class Topic13_Window_Tab {
	WebDriver driver;
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
public void TC_01_Window() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//get ra ID của windown đang đung
	String ParentID = driver.getWindowHandle();
	String ParentTitle = driver.getTitle();
	
	//Switch to children ID
	//SwitchToWindownByID(ParentID);
	//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ChildrenID@gmail.com");
	//Quay về Trang ban đầu
	//String ChildrenID = driver.getWindowHandle();
	//SwitchToWindownByID(ChildrenID);
	//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ParentID@gmail.com");
	
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	SwitchToWindownByTitle("Facebook - Đăng nhập hoặc đăng ký");
	SleepInSecond(2);
	
	SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	SwitchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	SleepInSecond(2);
	
	SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
	driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
	SwitchToWindownByTitle("Shopping online - Buy online on Lazada.vn");
	SleepInSecond(2);
	
	SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	SwitchToWindownByTitle("Google");
	CloseWindown(ParentID);
	//SwitchToWindownByID(ParentID);
	//String currenttitle = driver.getTitle();
	//System.out.println("CurrentTitle" +currenttitle);
	//Assert.assertEquals(ParentTitle, driver.getTitle());
	SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
	driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("thubtp@gmail.com");
}
	@Test
	public void TC_Kyna() {
		driver.get("https://kyna.vn/");
		String ParentTitle = driver.getTitle();
		String ParentID = driver.getWindowHandle();
		//scroll tới cuối trang
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		SleepInSecond(2);
		//Click vào icon youtube
		driver.findElement(By.xpath("//img[@alt='youtube']")).click();
		SleepInSecond(2);
		SwitchToWindownByTitle("(660) Kyna.vn - YouTube");
		SleepInSecond(7);
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
		
		//click vào icon facebook
		SwitchToWindownByTitle(ParentTitle);
		SleepInSecond(3);
		driver.findElement(By.xpath("//img[@alt='facebook']")).click();
		SwitchToWindownByTitle("Kyna.vn - Trang chủ | Facebook");
		SleepInSecond(7);
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		
		//Quay về parent page rồi close các tab 
		SwitchToWindownByTitle(ParentTitle);
		CloseWindown(ParentID);
		
	}
public void SwitchToWindownByID(String ParentID) {
	Set<String> allWindown = driver.getWindowHandles();
	for (String Id : allWindown) {
		System.out.println(Id + "Id");
		if (!Id.equals(ParentID)) {
			driver.switchTo().window(Id);
		}
	}
}
//switch vào từng window, lấy ra title của windown tương ứng
//Kiểm tra title của windown = title của windown mong muốn thì dừng

public void SwitchToWindownByTitle(String ExpectedTitle) {
	Set<String> allWindown = driver.getWindowHandles();
	for (String Id : allWindown) {
		driver.switchTo().window(Id);
		SleepInSecond(2);
		String WindownTitle = driver.getTitle();
		if (WindownTitle.equals(ExpectedTitle)) {
			break;
		}
	}
}
public void CloseWindown(String ParentID) {
	Set<String> allWindown = driver.getWindowHandles();
	for (String Id : allWindown) {
		driver.switchTo().window(Id);
		if (!Id.equals(ParentID)){
		driver.close();
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
@AfterClass
public void afterClass() {
driver.quit();
}
 
}