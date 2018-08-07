package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_User_Interactions {
	WebDriver driver;
	WebDriverWait wait;
	
  @Test
  public void TC01_MoveMouseToAnElement() {
	  driver.get("http://daominhdam.890m.com/");
	  WebElement HoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
	  Actions action = new Actions(driver);
	  action.moveToElement(HoverText).perform();
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(), "Hooray!");
  }
  
  @Test
  public void TC02_HoverMouse() {
	  driver.get("http://www.myntra.com/");
	  WebElement HoverText = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
	  WebElement Login = driver.findElement(By.xpath("//a[@data-track='login']"));
	  Actions action = new Actions(driver);
	  action.moveToElement(HoverText).perform();
	  Login.click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
  }
  
  @Test
  public void TC03_ClickAndHold() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List<WebElement> selectable = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  Actions action = new Actions(driver);
	  action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().build().perform();
	  List<WebElement> Selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(Selected.size(), 4);
  }
  
  @Test
  public void TC04_ClickAndHold_Control() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List<WebElement> selectable = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  Actions action = new Actions(driver);
	  action.keyDown(Keys.CONTROL).build().perform();
	  selectable.get(0).click();
	  selectable.get(2).click();
	  selectable.get(4).click();
	  selectable.get(6).click();
	  selectable.get(8).click();
	  action.keyUp(Keys.CONTROL).build().perform();
	  List<WebElement> Selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(Selected.size(), 5);
  }
  
  @Test
  public void TC05_DoubleClick() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement doubleclickbutton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  Actions action = new Actions(driver);
	  action.doubleClick(doubleclickbutton).perform();
	  Alert alert = driver.switchTo().alert();
	  String alertText = alert.getText();
	  Assert.assertEquals(alertText, "The Button was double-clicked.");
	  alert.accept();
  }
  
  @Test
  public void TC06_RightClick() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement RightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action = new Actions(driver);
	  action.contextClick(RightClickButton).perform();
	  WebElement QuitButton = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']/li[7]"));
	  action.moveToElement(QuitButton).perform();
	  QuitButton.click();
  }
  
  @Test
  public void TC07_DrapAndDrop() {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  Assert.assertEquals(target.getText(), "Drag the small circle here.");
	  Actions action = new Actions(driver);
	  action.dragAndDrop(source, target).perform();
	  Assert.assertEquals(target.getText(), "You did great!");
  }
  
  @Test
  public void TC08_DrapDrop2() {
	  driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	  WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement target = driver.findElement(By.xpath("//div[@id='droppable']"));
	  Assert.assertEquals(target.getText(), "Drop here");
	  Actions action = new Actions(driver);
	  action.dragAndDrop(source, target).perform();
	  Assert.assertEquals(target.getText(), "Dropped!");
  }
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  public void waitsleep() throws Exception {
	  Thread.sleep(2000);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
