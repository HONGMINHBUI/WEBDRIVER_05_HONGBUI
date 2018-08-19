package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Window {
	WebDriver driver;
	WebDriverWait wait;
  
@Test
public void TC02_SwitchToNewWindowByID() {
	driver.get("http://daominhdam.890m.com/");
	String ParentGUID = driver.getWindowHandle();
	driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	Set<String> allWindows = driver.getWindowHandles();
	    for (String runWindow : allWindows) {
	                if (!runWindow.equals(ParentGUID)) {
	                            driver.switchTo().window(runWindow);
	                            break;
	                }
	    }
	String NewTabTitle = driver.getTitle();
	Assert.assertEquals(NewTabTitle, "Google");
  }

@Test
public void TC03_SwitchToNewWindowByTitle() {
	driver.get("http://www.hdfcbank.com/");
	List <WebElement> PopUpList = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  if (PopUpList.size() > 0) {
	  driver.switchTo().frame(PopUpList.get(0));
	  WebElement ClosePopUp = driver.findElement(By.xpath("//div[@id='div-close']"));
	  JavascriptExecutor je = (JavascriptExecutor) driver;
	  je.executeScript("arguments[0].click();", ClosePopUp);  
	  }  
	driver.switchTo().defaultContent();
	String ParentPageGUID = driver.getWindowHandle();
	String ParentPageTitle = driver.getTitle();
	
	driver.findElement(By.xpath("//a[text()='Agri']")).click();
	switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
    
    driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	
	WebElement PolicyFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
	driver.switchTo().frame(PolicyFrame);
	driver.findElement(By.xpath("//p[@class='footer']//a[text()='Privacy Policy']")).click();
	switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	driver.switchTo().defaultContent();
	
	driver.findElement(By.xpath("//a[text()='CSR']")).click();
    
	closeAllWithoutParentWindows(ParentPageGUID);
	Assert.assertEquals(ParentPageTitle, "HDFC Bank: Personal Banking Services");
}
  
@BeforeClass
public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

public void switchToWindowByTitle(String title) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindows : allWindows) {
                driver.switchTo().window(runWindows);
                String currentWin = driver.getTitle();
                if (currentWin.equals(title)) {
                            break;
                }
    }
}

public boolean closeAllWithoutParentWindows(String parentGUID) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindows : allWindows) {
                if (!runWindows.equals(parentGUID)) {
                            driver.switchTo().window(runWindows);
                            driver.close();
                }
    }
    driver.switchTo().window(parentGUID);
    if (driver.getWindowHandles().size() == 1)
               return true;
    else
               return false;
}

  @AfterClass
public void afterClass() {
	  driver.quit();
  }
}
