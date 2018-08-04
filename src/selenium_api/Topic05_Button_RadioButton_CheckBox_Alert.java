package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic05_Button_RadioButton_CheckBox_Alert {
	WebDriver driver;
	WebDriverWait wait;
	
  @Test
  public void TC01_Button() {
	 driver.get("http://live.guru99.com/");
	 driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	 
	 clickElementByJavaScript("//a[@title='Create an Account']");
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  @Test
  public void TC02_CheckBox() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  String dualzone = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
	  clickElementByJavaScript(dualzone);
	  Assert.assertTrue(isElementSelected(dualzone));
	  unCheckTheCheckBox(dualzone);
	  Assert.assertFalse(isElementSelected(dualzone));
  }
  
  @Test
  public void TC03_RadioButton() {
	  driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
	  String petro = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
	  clickElementByJavaScript(petro);
	  reClick(petro);
	  Assert.assertTrue(isElementSelected(petro));
  }
  
  @Test
  public void TC04_JSAlert() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  Alert alert = driver.switchTo().alert();
	  String alertJSText = alert.getText();
	  Assert.assertEquals(alertJSText, "I am a JS Alert");
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
  }
  
  @Test
  public void TC05_JSConfirm() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  Alert alert = driver.switchTo().alert();
	  String alertJSText = alert.getText();
	  Assert.assertEquals(alertJSText, "I am a JS Confirm");
	  alert.dismiss();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
  }
  
  @Test
  public void TC06_JSPrompt() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  Alert alert = driver.switchTo().alert();
	  String alertJSText = alert.getText();
	  Assert.assertEquals(alertJSText, "I am a JS prompt");
	  alert.sendKeys("Hong test automation");
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Hong test automation");
  }
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  public void clickElementByJavaScript(String locator) {
	  WebElement element = driver.findElement(By.xpath(locator));
	  JavascriptExecutor je = (JavascriptExecutor) driver;
	  je.executeScript("arguments[0].click();", element);
  }
  public boolean isElementSelected(String locator) {
	  WebElement element = driver.findElement(By.xpath(locator));
	  return element.isSelected();
  }
  public void unCheckTheCheckBox(String locator) {
	  if (isElementSelected(locator))
		  clickElementByJavaScript(locator);
  }
  public void reClick(String locator) {
	  if (!isElementSelected(locator))
		  clickElementByJavaScript(locator); 
  }
 
@AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
