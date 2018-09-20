package TestNG_Package;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class DataProvider01 {
	WebDriver driver;
	
  @Test(dataProvider = "Login")
  public void TC01(String username, String password) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }

  @DataProvider
  public Object[][] Login() {
    return new Object[][] {
    	new Object[] { "hongtest1@gmail.com", "123123" },
    	new Object[] { "hongtest2@gmail.com", "123123" },
        new Object[] { "hongtest3@gmail.com", "123123" },
    };
  }
  
  @BeforeMethod
  public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
}
