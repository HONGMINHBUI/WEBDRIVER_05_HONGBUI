package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic10_WaitInSelenium {
	WebDriver driver;
	WebDriverWait wait;
	
@Test  
  public void TC01_ImplicitWait() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.findElement(By.xpath("//div[@id='start']/button")).click();
	  String HelloWorld = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
	  Assert.assertEquals(HelloWorld, "Hello World!");
  }
  
@Test  
  public void TC02_ExplicitWait() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rcMain']")));
	  String NoSelectedDate = driver.findElement(By.xpath("//span[@class='label']")).getText();
	  Assert.assertEquals(NoSelectedDate, "No Selected Dates to display.");
	  System.out.print(NoSelectedDate);
	  
	  WebElement Sep4 = driver.findElement(By.xpath("//td[@title='Tuesday, September 04, 2018']"));
	  Sep4.click();
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='4']/parent::td[@class='rcSelected']")));
	  String SelectedDate = driver.findElement(By.xpath("//span[@class='label']")).getText();
	  Assert.assertEquals(SelectedDate, "Tuesday, September 04, 2018");
  }
  
@Test  
  public void TC03_FluentWait() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  WebElement Countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  wait.until(ExpectedConditions.visibilityOf(Countdown));
	// Khởi tạo Fluent wait
	  new FluentWait<WebElement>(Countdown)
	             // Tổng time wait là 15s
	             .withTimeout(15, TimeUnit.SECONDS)
	              // Tần số mỗi 1s check 1 lần
	              .pollingEvery(1, TimeUnit.SECONDS)
	             // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	              .ignoring(NoSuchElementException.class)
	              // Kiểm tra điều kiện
	              .until(new Function<WebElement, Boolean>() {
	                  public Boolean apply(WebElement element) {
	                             // Kiểm tra điều kiện countdount = 00
	                             boolean flag =  element.getText().endsWith("00");
	                             System.out.println("Time = " +  element.getText());
	                             // return giá trị cho function apply
	                             return flag;
	                        }
	                 });
}	  
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
