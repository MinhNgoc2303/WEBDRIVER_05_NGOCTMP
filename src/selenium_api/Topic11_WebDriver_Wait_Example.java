package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;


public class Topic11_WebDriver_Wait_Example {
	WebDriver driver;
	WebDriverWait waitExplicit;
 
	@BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
  
  
  //@Test
  public void TS02_ExiplictWait() {
	  driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  //Step 02: 
	  /*presence*/
	  waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
	  
	  /*Visible*/
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
	  
	  WebElement TodayClick = driver.findElement(By.xpath("//a[text()='9']/parent::td")).click();
	  
	  //Step 05 - Wait cho đến khi "loader ajax" không còn visible
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
	  
	  //Step 07:
	  
  }
  @Test
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
