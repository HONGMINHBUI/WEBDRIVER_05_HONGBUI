package TestNG_Package;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Group {
  @Test (groups = "customer")
  public void TC01() {
	  System.out.println("Test case 01");
  }
  
  @Test (groups = "customer")
  public void TC02() {
	  System.out.println("Test case 02");
  }
  
  @Test (groups = "payment")
  public void TC03() {
	  System.out.println("Test case 03");
  }
  
  @Test (groups = "management")
  public void TC04() {
	  System.out.println("Test case 04");
  }
  
  @Test (groups = "customer")
  public void TC05() {
	  System.out.println("Test case 05");
  }
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
