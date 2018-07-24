package selenium_api;

import org.testng.annotations.Test;

	import java.util.Random;
	 import java.util.concurrent.TimeUnit;
	 import org.openqa.selenium.By;
	 import org.openqa.selenium.WebDriver;
	 import org.openqa.selenium.firefox.FirefoxDriver;
	 import org.testng.Assert;
	 import org.testng.annotations.BeforeClass;
	 import org.testng.annotations.AfterClass;
	 
public class Topic02_Xpath_Locator {
	WebDriver driver;
	
	@Test
	 public void TC_01_VerifyURLandTitle() {
	 String homePageTitle = driver.getTitle();
	 Assert.assertEquals(homePageTitle,"Home page");
	 
	 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	 
	 driver.navigate().back();
	 
	 String LogInURL = driver.getCurrentUrl();
	 Assert.assertEquals(LogInURL, "http://live.guru99.com/index.php/customer/account/login/");
	 
	 driver.navigate().forward();
	 
	 String CreatAccountURL = driver.getCurrentUrl();
	 Assert.assertEquals(CreatAccountURL, "http://live.guru99.com/index.php/customer/account/create/"); 	 
	}
	
	@Test
	public void TC_02_LoginEmpty() {

	driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	driver.findElement(By.xpath("//button[@id='send2']")).click();
	
	String usernameEmptyMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	Assert.assertEquals(usernameEmptyMessage,"This is a required field.");
	String passwordEmptyMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
	Assert.assertEquals(passwordEmptyMessage,"This is a required field.");
	
	}
	
	@Test
	public void TC_03_EmailInvalid() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
  		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
  		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
  		driver.findElement(By.xpath("//button[@id='send2']")).click();
  		
  		String usernameInvalidMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
  		Assert.assertEquals(usernameInvalidMessage,"Please enter a valid email address. For example johndoe@domain.com.");
  		String passwordEmptyMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
  		Assert.assertEquals(passwordEmptyMessage,"This is a required field.");
	}
	
	@Test
	public void TC_04_PasswordInvalid() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
  		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("tracybui.vn@gmail.com");
  		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
  		driver.findElement(By.xpath("//button[@id='send2']")).click();
  		
  		String passworInvalidMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
  		Assert.assertEquals(passworInvalidMessage,"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	 public void TC_05_CreateAnAccount() throws InterruptedException {
	 driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	 	
	 driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Hong");
	 driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Minh");
	 driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Bui");
	 driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("automation" + randomEmail() + "@gmail.com");
	 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
	 driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
	 driver.findElement(By.xpath("//button[@title='Register']")).click();
	 
	 String successMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
	 Assert.assertEquals(successMessage,"Thank you for registering with Main Website Store.");
	 
	 driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
	 driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	 
	 Thread.sleep(5000);
	 String HomePage = driver.getCurrentUrl();
	 Assert.assertEquals(HomePage,"http://live.guru99.com/index.php/");
	 	
}
	 public int randomEmail() {
	 Random random = new Random();
	 int number = random.nextInt(999999);
	 return number;	
	 }
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
  driver.quit();
  }

}