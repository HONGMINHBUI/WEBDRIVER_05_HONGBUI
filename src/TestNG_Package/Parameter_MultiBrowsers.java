package TestNG_Package;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Parameter_MultiBrowsers {
	WebDriver driver;

@Parameters("browser")
@BeforeClass
public void beforeClass(String browserName) {  
	  if (browserName.equals("chrome")) {
		  driver = new ChromeDriver();
	  } else if (browserName.equals("firefox")) {
		  driver = new FirefoxDriver();
	  } else {
		  driver = new SafariDriver();
	  }	  
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test(invocationCount = 5)
public void TC01() {
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hongtest1@gmail.com");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
	driver.findElement(By.xpath("//button[@id='send2']")).click();	  
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
