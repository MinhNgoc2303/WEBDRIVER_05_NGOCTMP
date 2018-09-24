package testNG;

import org.testng.annotations.Test;

public class TestNG_GroupTestcase {
  @Test(groups="customer")
  public void TC01_NewCustomer() {
	  System.out.println("Testcase01");
  }
  
  @Test(groups ="payment")
  public void TC02_EditCustomer() {
	  System.out.println("Testcase02");
  }
  
  @Test(groups = "manager", enabled = false)
  public void TC03_DeleteCustomer() {
	  System.out.println("Testcase03");
  }
  
  @Test(groups = "payment")
  public void TC04_ManageCustomer() {
	  System.out.println("Testcase04");
  }
  
  @Test(groups="customer")
  public void TC05_TransferCustomer() {
	  System.out.println("Testcase05");
  }
  
}
