package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic08_JavascriptExecutor {
	WebDriver driver;
	WebDriverWait wait;
	
@Test  
  public void TC01_JavascriptExcecutor() throws Exception {
	  NavigateToPage("http://live.guru99.com/");
	  String domain = (String) executeForBrowserElement("return document.domain");
	  Assert.assertEquals(domain, "live.guru99.com");
	  String PageURL = (String) executeForBrowserElement("return document.URL");
	  Assert.assertEquals(PageURL, "http://live.guru99.com/");
	  
	  WebElement MobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  executeForWebElement(MobileLink);
	  WebElement SamsungLink = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
	  executeForWebElement(SamsungLink);
	  
	  String AddToCartConfirmText = (String) executeForBrowserElement("return document.documentElement.innerText;");
	  Assert.assertTrue(AddToCartConfirmText.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  WebElement PolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  executeForWebElement(PolicyLink);
	  String PolicyPageTitle = (String) executeForBrowserElement("return document.title");
	  Assert.assertEquals(PolicyPageTitle, "Privacy Policy");
	 
	  scrollToBottomPage();
	  Thread.sleep(2000);
	  
	  WebElement cookie = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
	  Assert.assertEquals(cookie.getText(), "The number of items in your Wishlist.");	  
  }
  
@Test
 public void TC02_RemoveAttribute() throws Exception {
	 NavigateToPage("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	 WebElement iFrame = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
	 driver.switchTo().frame(iFrame);
	 WebElement LastNameField = driver.findElement(By.xpath("//input[@name='lname']"));
	 removeAttributeInDOM(LastNameField, "disabled");
	 LastNameField.sendKeys("Hong automation testing");
	 Thread.sleep(2000);
	 
	 WebElement SubmitButton = driver.findElement(By.xpath("//input[@type='submit']"));
	 executeForWebElement(SubmitButton);
	
	 Assert.assertTrue((driver.findElement(By.xpath("//div[contains(text(),\"Hong automation testing\")]"))).isDisplayed());
 }
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  public Object NavigateToPage(String URL) {
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  return js.executeScript("window.location = '" + URL + "'");
  }
  
  public Object executeForBrowserElement(String javaSript) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript(javaSript);
}
  
  public Object executeForWebElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].click();", element);
}
  
  public Object scrollToBottomPage() {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}
  
  public Object removeAttributeInDOM(WebElement element, String attribute) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
}

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
