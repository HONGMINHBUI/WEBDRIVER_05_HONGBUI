package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Frame_Iframe {
	WebDriver driver;
	WebDriverWait wait;
	
  @Test
  public void TC01_Handle_Iframe() {
	  driver.get("http://www.hdfcbank.com/");
	  List <WebElement> PopUpList = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  if (PopUpList.size() > 0) {
	  driver.switchTo().frame(PopUpList.get(0));
	  WebElement ClosePopUp = driver.findElement(By.xpath("//div[@id='div-close']"));
	  JavascriptExecutor je = (JavascriptExecutor) driver;
	  je.executeScript("arguments[0].click();", ClosePopUp);  
	  }  
	  driver.switchTo().defaultContent();
	  WebElement LookForIfame = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(LookForIfame);
	  String LookForText = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	  Assert.assertEquals(LookForText, "What are you looking for?");
	  
	  driver.switchTo().defaultContent();
	  WebElement BannerIfame = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(BannerIfame);
	  
	  By BannerListXpath = By.xpath("//div[@class='bannerimage-container']/img");
	  List <WebElement> BannerList = driver.findElements(BannerListXpath);
	  Assert.assertEquals(BannerList.size(), 6);
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(BannerListXpath));
	  System.out.println("All 6 images are presenced!");
	  
	  driver.switchTo().defaultContent();
	  List <WebElement> FliperBanner = driver.findElements(By.xpath("//img[@class='front icon']"));
	  Assert.assertEquals(FliperBanner.size(), 8);
	  int i = 0;
	  for (WebElement imageflip: FliperBanner) {
		  Assert.assertTrue(imageflip.isDisplayed());
		  i++;
		  System.out.println("Image number ["+ i +"] is displayed!");
	  }
  }
  
  @Test
  public void TC02_Handle_Iframe() {
	  
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