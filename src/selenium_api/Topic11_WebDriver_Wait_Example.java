package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic11_WebDriver_Wait_Example {
	WebDriver driver;
	WebDriverWait waitExplicit;
 
	@BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  waitExplicit = new WebDriverWait(driver, 30);
		  driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
	 //@Test
	  public void TS01_ImplicitWait() {
		  //step 01: Navigate to url:
		  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		  
		  //Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		  
		  // Step 03 - Click the start button
		  driver.findElement(By.xpath("//button[text()='Start']")).click();
		  
		  // Step 04 - Wait result text will appear and check result
		  Assert.assertEquals(driver.findElement(By.xpath("//div/h4[text()='Hello World!']")).getText(), "Hello World!");
		  
	  }
  
  @Test
  public void TS02_ExiplictWait() {
	  //Step 01: Navigate to url: 
	  driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  //Step 02: Wait to "Date Time Picker" is shown
	  /*presence*/
	  waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));
	  
	  /*Visible*/
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));
	  
	  // Step 03 - Print day selected (Before AJAX call) -> present not select = "No Selected Dates to display."
	  WebElement todayBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(todayBefore.getText(), "No Selected Dates to display.");
	  
	  // Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//a[text()='20']/parent::td")).click();
	 
	  //Step 05 - Wait cho đến khi "loader ajax" không còn visible
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  
	  
	  //Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility), must be check it have class change before and after selected
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='20']/parent::td[@class='rcSelected']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='20']/parent::td[@class='rcSelected']")).isDisplayed());
	  
	  //Step 07: Step 07 - Verify ngày đã chọn bằng = Saturday, September 23, 2017
	  //ISSUE :Because element changed after click, so should be modify element again
	  WebElement todayAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(todayAfter.getText(), "Thursday, September 20, 2018");
	  
	  
	  //String today ="09/09/2018";
	  //Split to day/month/year
	  //String day = "09";
	  //String month = "09";
	  //String year = "2018";
  }
  
  
  
  
  //@Test
  public void TS03_FluentWait() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  
	  WebElement CountDown = driver.findElement(By.xpath("//*[@id='javascript_countdown_time']"));
	  
	  // Create Fluent wait
	  new FluentWait<WebElement>(CountDown)
	             // Total time wait is 15s
	             .withTimeout(15, TimeUnit.SECONDS)
	              // frequency is 1s
	              .pollingEvery(1, TimeUnit.SECONDS)
	             // if result exception is no find element will skip
	              .ignoring(NoSuchElementException.class)
	              // Check condition
	              .until(new Function<WebElement, Boolean>() {
	                  public Boolean apply(WebElement element) {
	                             // Check condition countdount = 00
	                             boolean flag =  element.getText().endsWith("00");
	                             System.out.println("Time = " +  element.getText());
	                             // return value function apply
	                             return flag;
	                        }
	                 });
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
