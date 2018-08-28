package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Iframe_Windows {
	WebDriver driver;
	WebDriverWait wait;
  
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
	  By bannerImagesXpath = By.xpath("//div[@id='productcontainer']//img");
	  List <WebElement> bannerImages = driver.findElements(bannerImagesXpath);
	  int bannerImgNumber = bannerImages.size();
	  
	  //Check image =6
	  Assert.assertEquals(bannerImgNumber, 6);
	  
	  
	//Check all img are presence
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImagesXpath));
	  //Switch to default content()
	  driver.switchTo().defaultContent();
	  
	  //Step 05 - Verify flipper banner được hiển thị và có 8 element
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed(), true);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
	  
	  List <WebElement> flipBannerImages = driver.findElements(By.xpath("//div[@class=''flipBanner']//img[@class='front icon']"));
	  int flipBannerImagesNumber = flipBannerImages.size();
	  Assert.assertEquals(flipBannerImagesNumber, 8);
	  int i=0;
	  
	  
	  //For each 
	  for(WebElement image : bannerImages) {
		  Assert.assertTrue(image.isDisplayed());
		  i++;
		  
		  System.out.println("Image [" + i + "] displayed!");
	  }
	  
  }
  
  //@Test
  public void TS02_handleWindows() {
	  driver.get("http://daominhdam.890m.com/");
	  
	  //Case 01: chỉ có 2 windown or 2 tab: SWITCH VIA GUID
	  //Get GUID parent page
	  String parentID = driver.getWindowHandle();
	  String parentGUID = driver.getWindowHandle();
	  
	  System.out.println("Title before = "+ driver.getTitle());
	  System.out.println("Parent ID = " + parentGUID );
	  
	  //Click new windown
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	  
	  //Switch new tab or new windown
	  switchToChildWindowByGUID(parentID);
	  switchToWindowByTitle("Google");
	  //Verify switch success
	  String googleTitle = driver.getTitle();
	  System.out.println("Title after = "+ googleTitle);
	  Assert.assertEquals(googleTitle, "Google");
	  
	  closeAllWithoutParentWindows(parentGUID);
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	  
	  //Case 02: >= 2 windown or 2 tab: SWITCH BY TITLE PAGE
	  //Click 
	  // case 1: 2 windows/2tabs:
	  
  }
  
  //@Test
  public void TS03_HanldeCloseWindows() {
	  driver.get("https://www.hdfcbank.com/");
	  
	  String parentGUID = driver.getWindowHandle();
	  System.out.println("Parent ID = " + parentGUID );
	  
	  //Check if have advertiment , will click close
	  List<WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  System.out.print("Number element = " + notificationIframe.size() );
	  
	  if(notificationIframe.size() > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
		  
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Closed popup!");
	
		  driver.switchTo().defaultContent();
	  }
	  
	  //Click "agri"
	  driver.findElement(By.xpath("//a[text()='Agri']")).click();
	  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  //click account details
	  driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  
	  //Click Privacy Policy link (into frame) -> Open new tab/window -> Switch to new tab/window
	  //Switch to frame
	  WebElement footerframe = driver.findElement(By.xpath("//frame[@name='footer']"));
	  driver.switchTo().frame(footerframe);
	  //Click Private Policy and check switch to new tab
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  //Click CSR 
	  driver.findElement(By.xpath("//a[text()='CSR']")).click();
	  
	 //Back main page and close all page without main page
	  closeAllWithoutParentWindows(parentGUID);
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  

  public void switchToChildWindowByGUID(String parentID) {
	  			//Get all curent windown
              Set<String> allWindows = driver.getWindowHandles();
              
              //Duyệt qua tất cả các windown/tab
              for (String runWindow : allWindows) {
            	  //nếu windown/tab ID khác parentID thì switch qua
                          if (!runWindow.equals(parentID)) {
                                      driver.switchTo().window(runWindow);
                                      break;
                          }
              }
  }
  
  //Switch to child Windows (greater than 2 windows and title of the pages are unique)
  public void switchToWindowByTitle(String ExpectedTitle) {
	  
	  //Get all current windown/tab
      Set<String> allWindows = driver.getWindowHandles();
      //Lặp qua từng windown/tab
      for (String runWindows : allWindows) {
    	  			//Switch qua từng windown trước
                  driver.switchTo().window(runWindows);
                  //Get ra title của từng windown
                  String currentWin = driver.getTitle();
                  //Nếu title của windown ( đã get ở trên) mà = vs expected title truyền vào thì ngưng 
                  if (currentWin.equals(ExpectedTitle)) {
                              break;
                  }
      }
}
  
  //Close all windows without parent window
  public boolean closeAllWithoutParentWindows(String parentGUID) {
	  //Get all cureent windown/tab
      Set<String> allWindows = driver.getWindowHandles();
      
      //Duyệt qua từng windown/tab
      for (String runWindows : allWindows) {
    	  
    	  			//Kiểm tra nếu window/ tab # parrentID
                  if (!runWindows.equals(parentGUID)) {
                	  
                	  			//Switch qua window/tab đó
                              driver.switchTo().window(runWindows);
                              
                              //Sau đó closed
                              driver.close();
                  }
      }
      
      //Switch về parrent windows
      driver.switchTo().window(parentGUID);
      //Check xem còn đúng 1 window hay ko 
      if (driver.getWindowHandles().size() == 1)
                 return true;
      else
                 return false;
}

}
