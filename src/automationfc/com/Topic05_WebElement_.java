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
public class Topic05_WebElement_ {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
	//
	driver = new FirefoxDriver();
	//thoi gian cho xxxx second
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//mo url
	driver.get("https://demo.nopcommerce.com/");
}
	@SuppressWarnings("deprecation")
	@Test
public void TC_01_Web_Element(Object WebElement) {
		//Muốn thao tác với element thì phải tìm element trước
		
		//Tìm 1 Element
		driver.findElement(By.id(""));
		
		//TÌm nhiều elements
		driver.findElements(By.id(""));
		
		//Nếu chỉ tao tác với element 1 lần thì không cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple");//**
		
		//nếu thao tác với element nhiều lần thì nên khai báo biến 
		WebElement searchTextbox= driver.findElement(By.id("small-searchterms"));
		searchTextbox.clear();//**
		searchTextbox.sendKeys("Apple");//**
		searchTextbox.getAttribute("value");//**
		
		//đếm xem có bao nhiêu element thỏa mãn đk
		//verify số lượng element trả ra như mong đợi
		//thao tác với  tất cả các element giống nhau trong 1 page (checkbox/textbox/...)
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		//verify có đúng 6 textboxes tại form đky 
		Assert.assertEquals(checkboxes.size(), 6);
		
		WebElement SingleElement = driver.findElement(By.className(""));
		
		//textboxt/textArea/Editable Dropdown
		//Dữ liệu được toàn vẹn
		SingleElement.clear();
		SingleElement.sendKeys("");
		//button /link/radio/checkbox/custom dropdown
		SingleElement.click();
		
		//Các hàm có tiền tố bắt đầu bằng get luôn trả về dữ liệu
		//getTitle/getCurrentURl/getPageSource/GetAttribute/getCssValue/gettext...
		SingleElement = driver.findElement(By.xpath("//input[@id='FirstName']"));
		SingleElement.getAttribute("value");
		
		SingleElement = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		SingleElement.getAttribute("placeholder");
		
		//Lấy ra giá trị của các thuộc tính CSS- thường dùng để testGUI
		//font/size/color /background
		SingleElement = driver.findElement(By.cssSelector(".search-box-button"));
		SingleElement.getCssValue("background-color");
		//#4ab2f1
		SingleElement.getCssValue("text-transform");
		
		//lấy ra tọa độ của element so với page hiện tại (get góc bên ngoài element)
		SingleElement.getLocation();
		//Lấy ra kích thước của element (rộng x cao)-> gẻ góc bên trong element
		
		SingleElement.getSize();
		//lấy ra location + size
		SingleElement.getRect();
		
		//chụp hình lỗi -> đưa vào  HTM report
		SingleElement.getScreenshotAs(OutputType.FILE);
	
		SingleElement= driver.findElement(By.cssSelector(".search-box-button"));
		String SearchButtonTagname = SingleElement.getTagName();
		
		searchTextbox = driver.findElement(By.xpath("//" +SearchButtonTagname+ "div[@class='inputs']/input[not(@type='checkbox')]"));
		//Lấy ra text của element (header/link/Message...
		SingleElement.getText();
		//các hàm có tiền tố isXXX thì trả về kiểu Boolean true/false
		
		//Kiểm tra xem 1 element là hiển thị cho người dùng thao tác hay không 
		SingleElement.isDisplayed();
		
		//kiểm tra 1 element cho phép thao tác hay không
		//true: cho phép thao tác
		//false : ko cho phép thao tác
		SingleElement.isEnabled();
		
		//Kiểm tra element đã được chọn hay cha
		//true: đã chọn rồi
		//false: chwa được chọn
		
		SingleElement.isSelected();
		
		//nó thay cho hành vi ENTER vào textbox/click vào button
		//Chỉ dùng cho form login/search/đky

		SingleElement = driver.findElement(By.id("small-searchterms"));
		SingleElement.sendKeys("Apple");
		SingleElement.submit();
		
}
 
	@Test
public void TC_02_Class() {

}
	@Test
public void TC_03_Name() {

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