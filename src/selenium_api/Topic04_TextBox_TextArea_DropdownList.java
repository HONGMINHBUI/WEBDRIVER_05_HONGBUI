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

public class Topic04_TextBox_TextArea_DropdownList {
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
	  driver.get("http://demo.guru99.com/v4");
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145310");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ysYhute");
      driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
      driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
      
      WebElement Name = driver.findElement(By.xpath("//input[@name='name']"));
      WebElement Gender = driver.findElement(By.xpath("//input[@value='f']"));
      WebElement DOB = driver.findElement(By.xpath("//input[@name='dob']"));
      WebElement Address = driver.findElement(By.xpath("//textarea[@name='addr']"));
      WebElement City = driver.findElement(By.xpath("//input[@name='city']"));
      WebElement State = driver.findElement(By.xpath("//input[@name='state']"));
      WebElement PIN = driver.findElement(By.xpath("//input[@name='pinno']"));
      WebElement Phone = driver.findElement(By.xpath("//input[@name='telephoneno']"));
      WebElement Email = driver.findElement(By.xpath("//input[@name='emailid']"));
      WebElement Password = driver.findElement(By.xpath("//input[@name='password']"));
      WebElement Submit = driver.findElement(By.xpath("//input[@name='sub']"));
      
      Name.sendKeys("Hong Bui");
      Gender.click();
      DOB.sendKeys("1986-05-03");
      Address.sendKeys("Cogges");
      City.sendKeys("Witney");
      State.sendKeys("Oxfordshire");
      PIN.sendKeys("123123");
      Phone.sendKeys("07888368186");
      Email.sendKeys("auto" + randomEmail() + "@gmail.com");
      Password.sendKeys("123456");
      Submit.click();
      Thread.sleep(3000);
      
      String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
      String AddressID = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
      System.out.println(customerID);
      System.out.println(customerName);
      System.out.println(AddressID);
      
      driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
      driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
      driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
      
      WebElement EditName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input"));
      WebElement EditAddress = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea"));
      WebElement EditCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input"));
      
      Assert.assertEquals(customerName, EditName.getAttribute("value"));
      Assert.assertEquals(AddressID, EditAddress.getText());
      
      EditAddress.clear();
      EditAddress.sendKeys("Manor Road");
      EditCity.clear();
      EditCity.sendKeys("Stanlake");
      driver.findElement(By.xpath("//input[@name='sub']")).click();
      String AddressIDNew = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
      Assert.assertEquals(AddressIDNew, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
      String CityID = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
      Assert.assertEquals(CityID, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText()); 
      
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
