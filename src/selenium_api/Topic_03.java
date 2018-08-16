package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03 {
	WebDriver driver;
	

	@BeforeClass
	public void BeforeClass() {
		
		//System.setProperty("webdriver.chrome.driver", ".//driver//chromedriver.exe"); 
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.ie.driver", ".//driver//IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		 //driver = new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.get("http://daominhdam.890m.com/");
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
	  }
	
  @Test 
  public void TC_01_CheckDisplay() throws Exception {
	  
	 WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));
	 WebElement CheckBox = driver.findElement(By.xpath("//input[@id='under_18']"));
	 WebElement EducationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	 
	 Assert.assertTrue(CheckControlDisplay(emailTextBox));
	 Assert.assertTrue(CheckControlDisplay(CheckBox));
	 Assert.assertTrue(CheckControlDisplay(EducationTextArea));
  
  if(CheckControlDisplay(emailTextBox))  {
	  emailTextBox.sendKeys(" Automation Testing");
  }
  
  if(CheckControlDisplay(CheckBox)) {
	  CheckBox.click();
  }
  
  if(CheckControlDisplay(EducationTextArea)) {
	  CheckBox.sendKeys("Automation Testing");
  }
  
  Thread.sleep(5000);
  }
  
  public boolean CheckControlDisplay(WebElement element) {
	  return element.isDisplayed();
  }
  
  @Test 
  public void TC_02_CheckEnableAndDisable() {
	  WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));
	  WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement Education = driver.findElement(By.xpath("//textarea[@id='edu']"));
	  WebElement JobRole1 = driver.findElement(By.xpath("//*[@id='job1']"));
	  WebElement Interests = driver.findElement(By.xpath("//input[@id='development']"));
	  WebElement Slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
	  WebElement ButtonEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
	  
	  ControlElement(emailTextBox);
	  ControlElement(age);
	  ControlElement(Education);
	  ControlElement(JobRole1);
	  ControlElement(Interests);
	  ControlElement(Slider01);
	  ControlElement(ButtonEnable);	  
	  
  }
  
  public void ControlElement (WebElement element) {
	  if(element.isEnabled()) {
		  System.out.print("Element is enabled\n");
	  }
	  else {
		  System.out.print("Element is enabled");
	  }
	  
  }
  
 
  
  
  public void TC_02_CheckDisable() {
	  WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
	  WebElement RadioButondisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
	  WebElement Biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
	  WebElement JobRole2 = driver.findElement(By.xpath(".//*[@id='job2']"));
	  WebElement InterestsUnCheck = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
	  WebElement Slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
	  WebElement ButtonDisable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
	  
	  ControlElementDisable(pass);
	  ControlElementDisable(RadioButondisable);
	  ControlElementDisable(Biography);
	  ControlElementDisable(JobRole2);
	  ControlElementDisable(InterestsUnCheck);
	  ControlElementDisable(Slider02);
	  ControlElementDisable(ButtonDisable);
	  
  }
  
  
  public void ControlElementDisable (WebElement element) {
	  if(element.isEnabled()) {
		  System.out.print("Element is enabled\n");
	  }
	  else {
		  System.out.print("Element is enabled");
	  }
	  
  }

  @Test
  public void TC_03_KiemTraPhanTuHienThiTrenTrang() {
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement Interests =  driver.findElement(By.xpath("//input[@id='development']"));
	  
	  isControlSelected(ageRadioButton);
	  isControlSelected(Interests);
	   
  }
  
  public void isControlSelected(WebElement element) {
	  if(!element.isSelected()) {
		  element.click();
	  } 
	  
		  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}


