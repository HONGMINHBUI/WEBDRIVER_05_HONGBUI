package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Topic4_TextBox_TextArea_DropdownList2 {
	WebDriver driver;
	
  @Test
  public void TC01_DropdownList() {
	  driver.get("http://daominhdam.890m.com/");
	  Select Job1 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  Assert.assertFalse(Job1.isMultiple());
	  Job1.selectByVisibleText("Automation Tester");
	  Assert.assertEquals("Automation Tester",Job1.getFirstSelectedOption().getText());
	  Job1.selectByValue("manual");
	  Assert.assertEquals("Manual Tester",Job1.getFirstSelectedOption().getText());
	  Job1.selectByIndex(3);
	  Assert.assertEquals("Mobile Tester",Job1.getFirstSelectedOption().getText());
	  Assert.assertEquals(5, Job1.getOptions().size());
  }
  
  @Test
  public void TC02_TextBox_TextArea() throws Exception {
	  String name, dob, address, city, state, pin, phone, email, pass, newaddress, newcity;
	  By Name = By.xpath("//input[@name='name']");
      By DOB = By.xpath("//input[@name='dob']");
      By Address = By.xpath("//textarea[@name='addr']");
      By City = By.xpath("//input[@name='city']");
      By State = By.xpath("//input[@name='state']");
      By PIN = By.xpath("//input[@name='pinno']");
      By Phone = By.xpath("//input[@name='telephoneno']");
      By Email = By.xpath("//input[@name='emailid']");
      By Password = By.xpath("//input[@name='password']");
	  
      driver.get("http://demo.guru99.com/v4");
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145310");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ysYhute");
      driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
      driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
      
      name = "Hong Bui";
      dob = "1986-05-03";
      address = "Cogges";
      newaddress = "Manor Road";
      city = "Witney";
      newcity = "Stanlake";
      state = "Oxfordshire";
      pin = "123123";
      phone = "07888368186";
      email = "auto" + randomEmail() + "@gmail.com";
      pass = "123456";
      
      driver.findElement(Name).sendKeys(name);
      driver.findElement(By.xpath("//input[@value='f']")).click();
      driver.findElement(DOB).sendKeys(dob);
      driver.findElement(Address).sendKeys(address);
      driver.findElement(City).sendKeys(city);
      driver.findElement(State).sendKeys(state);
      driver.findElement(PIN).sendKeys(pin);
      driver.findElement(Phone).sendKeys(phone);
      driver.findElement(Email).sendKeys(email);
      driver.findElement(Password).sendKeys(pass);
      
      driver.findElement(By.xpath("//input[@name='sub']")).click();
      Thread.sleep(3000);
      
      String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();	 
      System.out.println(customerID);
      
      driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
      driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
      driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
      
      Assert.assertEquals(name, driver.findElement(Name).getAttribute("value"));
      Assert.assertEquals(address, driver.findElement(Address).getText());
      
      driver.findElement(Address).clear();
      driver.findElement(Address).sendKeys(newaddress);
      driver.findElement(City).clear();
      driver.findElement(City).sendKeys(newcity);
      driver.findElement(By.xpath("//input[@name='sub']")).click();
      
      Assert.assertEquals(newaddress, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
      Assert.assertEquals(newcity, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText()); 
      
  }
  
  	 public int randomEmail() {
	 Random random = new Random();
	 int number = random.nextInt(999999);
	 return number;	
	 }

@BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
