package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Iframe_Windows {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TS01() {
	  driver.get("https://www.hdfcbank.com/");
	  //Step 02- Check close popup displayed (exist click close icon)
	  List<WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  System.out.print("Number element = " + notificationIframe.size() );
	  
	  if(notificationIframe.size() > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
		  
		  //JavaScript click to element
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Closed popup!");
		  //ISSUE 03: switch between 2 iframe (switch to default content)
		  //Switch to Top Windows
		  driver.switchTo().defaultContent();
	  }
	  
	  //Step 03 - Verify text is show
	  //Switch to iframe
	  WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(lookingForIframe);
	  
	  //Check message displayed
	  String messText = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	  Assert.assertEquals(messText, "What are you looking for?");
	  
	  //Switch to Content default
	  driver.switchTo().defaultContent();
	  
	  //Step 04 : Verify banner iframe
	  WebElement bannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(bannerIframe);
	  
	  //Verify banner image
	  List <WebElement> bannerImages = driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
	  int bannerImgNumber = bannerImages.size();
	  
	  //Check image =6
	  Assert.assertEquals(bannerImgNumber, 6);
	  
	  //Check all img displayed
	  for(WebElement image : bannerImages) {
		  Assert.assertTrue(image.isDisplayed());
		  System.out.println("Image displayed!");
	  }
	  
  }
  
  //@Test
  public void f1() {
  }
  
  //@Test
  public void f2() {
  }
  
  //@Test
  public void f3() {
  }
  
  //@Test
  public void f4() {
  }
  
  //@Test
  public void f5() {
  }
  
  //@Test
  public void f6() {
  }

  @AfterClass
  public void afterClass() {
  }

}
