package TestNG_Package;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Priority {
	@Test (groups = "customer", priority = 5)
	  public void TC01() {
		  System.out.println("Test case 01");
	  }
	  
	  @Test (groups = "customer", priority = 4)
	  public void TC02() {
		  System.out.println("Test case 02");
	  }
	  
	  @Test (groups = "payment", enabled = false)
	  public void TC03() {
		  //enabled = false => skip Test case
		  System.out.println("Test case 03");
	  }
	  
	  @Test (groups = "management", priority = 2)
	  public void TC04() {
		  System.out.println("Test case 04");
	  }
	  
	  @Test (groups = "customer", priority = 1)
	  public void TC05() {
		  System.out.println("Test case 05");
	  }
}
