package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Topic03_WebBrowser_WebElement {
	WebDriver driver;
	
@Test
  public void TC01_CheckElementDisplay() {
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automation");
	  driver.findElement(By.xpath("//input[@id='under_18']")).click();
	  driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("automation");	  
  }
  
  @Test
  public void TC02_CheckElementEnable() throws Exception {
	 WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
	 WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	 WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
	 WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	 
	 isControlEnabled(emailTextbox);
	 isControlEnabled(ageRadioButton);
	 isControlEnabled(passwordTextbox);
	 isControlEnabled(educationTextArea);
  }
  
  public void isControlEnabled(WebElement element) {
	  if(element.isEnabled()) {
		  System.out.println("Element is enabled");  
	  } else {
		  System.out.println("Element is disable");
	  }
  } 
  
  @Test
  public void TC03_CheckElementSelected() {
	  driver.findElement(By.xpath("//input[@id='under_18']")).click();
	  driver.findElement(By.xpath("//input[@id='development']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@id='development']")).isSelected());
  }
  
	  @BeforeClass
  public void beforeClass() {
	  driver = new ChromeDriver();
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

@AfterClass
  public void afterClass() throws Exception {
	  Thread.sleep(5000);
	  driver.quit();
  }

}
