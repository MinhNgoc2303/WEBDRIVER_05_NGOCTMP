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
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_ButtonRadioButtonAlertCheckBox {
	WebDriver driver;
	
   @BeforeClass
  public void beforeClass() {
	   driver = new FirefoxDriver();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   
  }
   //@Test
  public void TC_01_Button() {
	   driver.get("http://live.guru99.com/");
	   
	   //click My account and check url
	   // Su dung click build by selenium, only work with enable button
	   driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	   Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
	   Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	  
	   //Click Create an Account and check url
	   // using click by java script , with visible or invisible button all check 
	   clickElementByJavascript("//a[@title='Create an Account']");
	   driver.findElement(By.xpath("//a[@title='Create an Account']"));
	   Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
	   Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }
   
   //@Test
   public void TC_02_CheckBox() throws Exception {
	   
	  //Case 01: lable đang visible - nó có thể click dc 
 	 driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
 	 //WebElement DualzoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
 	 //DualzoneCheckbox.click();
 	 //Thread.sleep(3000);
 	 //assert failed vì ko thể isSelected cho 1 lable
 	 //Assert.assertTrue(DualzoneCheckbox.isSelected());

 	 // Case 02: Input: Invisible - không thể click dc 
 	 //WebElement RainCheeckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
 	 //RainCheeckbox.click();
 	 //Khi Assert se pass - is selected input
 	 //Assert.assertTrue(DualzoneCheckbox.isSelected());
 	 //Case 03: mix case 01 và 02 => phải khai báo 2 element
 	  
 	  //Case 04: Chỉ dùng 1 element vừa click vừa verify dc 
 	  String DualZoneCheckBox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
 	  //Javascript click: element visible/invisible/clickable
 	  clickElementByJavascript(DualZoneCheckBox);
 	  Assert.assertTrue(isElementSelected(DualZoneCheckBox));
 	  
 	  //Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa select thì sẽ select
 	  UnCheckTheCheckbox(DualZoneCheckBox);
 	  Assert.assertFalse(isElementSelected(DualZoneCheckBox));
   }
   	
   
   //@Test
   public void TC_03_CheckRadioButton() throws Exception {
	   
	  //Case 01: lable đang visible - nó có thể click dc 
 	 driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
 	  
 	  //Case 04: Chỉ dùng 1 element vừa click vừa verify dc 
 	  String RadioButton = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
 	  //Javascript click: element visible/invisible/clickable
 	  clickElementByJavascript(RadioButton);
 	  Assert.assertTrue(isElementSelected(RadioButton));
 	  
 	  //Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa select thì sẽ select.
 	  //Radio buton ko co uncheck dc, chi co checkbok moi uncheck dc thoi 
 	  UnCheckTheCheckbox(RadioButton);
 	  //Assert.assertFalse(isElementSelected(RadioButton));
   }
   
  // @Test
   public void TC_04_Alert() throws Exception {
	   
	  //Case 01: lable đang visible - nó có thể click dc 
	   driver.get("http://daominhdam.890m.com/");
 	  
	   //Step 02 - Click vào button: Click for JS Alert
	   driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	   
	   //Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
	   Alert alert = driver.switchTo().alert();
	   String alertjsMess = alert.getText();
	   Assert.assertTrue(alertjsMess.equals("I am a JS Alert"));
	   alert.accept();
	   
	   //Step 04: Verify
	   Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
	   
	   
	   
	   
 	  
 	 
 	  
   }
  
 //@Test
   public void TC_05_ClickJSConfirm() throws Exception {
	   
	  //Step 01: navigate to url: 
 	 driver.get("http://daominhdam.890m.com/");
 	  
 	  //Step 02: Click on JS confirm
 	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
 	  
 	  //Verify message display is correctly
 	  Alert alert = driver.switchTo().alert();
 	  String JSConfirmMess = alert.getText();
 	  Assert.assertTrue(JSConfirmMess.equals("I am a JS Confirm"));
 	  
 	  //Cancel and verify message is cancle
 	  alert.dismiss();
 	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
 	  
   }
   
 @Test
 public void TC_06_ClickJSPrompt() throws Exception {
	   
	  //Navigate to url: 
	  driver.get("http://daominhdam.890m.com/");
	  
	  //Click on JS prompt
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  
	  //Verify message display is correctly
	  Alert alert = driver.switchTo().alert();
	  String JSConfirmMess = alert.getText();
	  Assert.assertTrue(JSConfirmMess.equals("I am a JS prompt"));
	  
	  //Input text daominhdam and verify message
	  alert.sendKeys("daominhdam");
	  
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: daominhdam");
	  
 }
   
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  
  public void clickElementByJavascript(String locator) {
	  	WebElement element = driver.findElement(By.xpath(locator));
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
  
  public boolean isElementSelected (String locator) {
	  	WebElement elementCheckbox = driver.findElement(By.xpath(locator));
	  	return elementCheckbox.isSelected();
  }
  
  public void UnCheckTheCheckbox(String locator) {
	  if (isElementSelected(locator)) {
		  clickElementByJavascript(locator);
	}
	  
  }

}
