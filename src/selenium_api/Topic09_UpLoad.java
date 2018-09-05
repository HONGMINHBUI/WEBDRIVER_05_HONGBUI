package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic09_UpLoad {
	WebDriver driver;
	WebDriverWait wait;
  
@Test
public void TC01_SendKeys_Firefox() {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	String projectDirectory = System.getProperty("user.dir");
	String fileName = "CodeDam.png";
	String linkUpLoad = projectDirectory  + "/images/" + fileName;
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(linkUpLoad);
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']")).isDisplayed());
	driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+ fileName + "']")).isDisplayed());
  }

@Test
public void TC02_MultipleUpload() {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	String projectDirectory = System.getProperty("user.dir");
	String fileName01 = "CodeAuto01.png";
	String fileName02 = "CodeAuto02.png";
	String fileName03 = "CodeAuto03.png";
	String linkUpLoad01 = projectDirectory  + "/images/" + fileName01;
	String linkUpLoad02 = projectDirectory  + "/images/" + fileName02;
	String linkUpLoad03 = projectDirectory  + "/images/" + fileName03;
	String MultipleUploadLink[] = {linkUpLoad01, linkUpLoad02, linkUpLoad03};
	for (int i=0; i < MultipleUploadLink.length; i++) {
		WebElement AddFileButton = driver.findElement(By.xpath("//input[@type='file']"));
		AddFileButton.sendKeys(MultipleUploadLink[i]);
	}
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName01 + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName02 + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName03 + "']")).isDisplayed());
	
	List <WebElement> StartButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
	for (WebElement Start: StartButton) {
		Start.click();
	}
	Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+ fileName01 + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+ fileName02 + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+ fileName03 + "']")).isDisplayed());
	
	List <WebElement> Images = driver.findElements(By.xpath("//span[@class='preview']//img"));
	for (WebElement Image: Images) {
		Assert.assertTrue(CheckImageLoaded (Image));
	}
}

@Test
public void TC03_SendKeys_Chrome() {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	String projectDirectory = System.getProperty("user.dir");
	String fileName = "CodeDam.png";
	String linkUpLoad = projectDirectory  + "/images/" + fileName;
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(linkUpLoad);
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']")).isDisplayed());
	driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+ fileName + "']")).isDisplayed());
}

@Test
public void TC04_UploadFile() throws Exception {
	driver.get("https://encodable.com/uploaddemo/");
	String projectDirectory = System.getProperty("user.dir");
	String fileName = "CodeDam.png";
	String linkUpLoad = projectDirectory  + "/images/" + fileName;
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(linkUpLoad);
	
	Select UpLoadTo = new Select(driver.findElement(By.xpath("//select[@name='subdir1']")));
	UpLoadTo.selectByVisibleText("/uploaddemo/files/");
	Assert.assertEquals("/uploaddemo/files/",UpLoadTo.getFirstSelectedOption().getText());
	
	String Email = "auto" + randomNumber() + "@gmail.com";
	String Folder = "Minh" + randomNumber();
	String Name = "Hong" + randomNumber();
	
	driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(Folder);
	driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(Email);
	driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(Name);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
	
	WebElement EmailAddress = driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='Email Address: " + Email + "']"));
	WebElement FirstName = driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='First Name: " + Name + "']"));
	WebElement FileName = driver.findElement(By.xpath("//a[text()='" + fileName + "']"));
	Assert.assertTrue(EmailAddress.isDisplayed());
	Assert.assertTrue(FirstName.isDisplayed());
	Assert.assertTrue(FileName.isDisplayed());
	
	driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
	driver.findElement(By.xpath("//a[text()='" + Folder + "']")).click();
	WebElement ConfirmFileName = driver.findElement(By.xpath("//a[text()='" + fileName + "']"));
	Assert.assertTrue(ConfirmFileName.isDisplayed());
}

  @BeforeClass
  public void beforeClass() {
	  //driver = new ChromeDriver();
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  public Object ClickElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].click();", element);
}
  
  public int randomNumber() {
	 Random random = new Random();
	 int number = random.nextInt(999999);
	 return number;	
	 }
  
  public boolean CheckImageLoaded(WebElement image) {
       JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
       return (boolean) jsExecutor.executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
 }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
