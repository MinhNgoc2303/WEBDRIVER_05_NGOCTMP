package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic09_JavaScriptExecutor {
	WebDriver driver;
	
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TS01_JavascriptExecutor() {
	  OpenAnyURLWithJS("http://live.guru99.com/");
	  
	  String HomePgaeDomain = (String) executeJSForBrowser("return document.domain;");
	  Assert.assertEquals(HomePgaeDomain, "live.guru99.com");
	  
	  String HomePgaeURL = (String) executeJSForBrowser("return document.URL;");
	  Assert.assertEquals(HomePgaeURL, "http://live.guru99.com/");
	  
	  //Open mobile page
	  WebElement MobileElement = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  HighlightElement(MobileElement);
	  executeJSForWebElement(MobileElement);
	  
	  //Add Samsum to cart
	  WebElement SamSungGalaxy = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
	  HighlightElement(SamSungGalaxy);
	  executeJSForWebElement(SamSungGalaxy);
	  
	  //Verify message showed
	  String SamSungAdded = (String) executeJSForBrowser("return document.documentElement.innerText");
	  Assert.assertTrue(SamSungAdded.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  //Open Privacy policy page
	  WebElement PrivacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  HighlightElement(PrivacyPolicy);
	  executeJSForWebElement(PrivacyPolicy);
	  
	  //Verify title of Page
	  String PrivacyURL = (String) executeJSForBrowser("return document.title;");
	  Assert.assertEquals(PrivacyURL, "Privacy Policy");
	  
	  //Scroll updown page
	  scrollJSToBottomPage();
	  
	  //Verify data has only shown with 1 xpath 
	  WebElement WishListContent = driver.findElement(By.xpath("//th[text()='WISHLIST']/following-sibling::td[text()='An encrypted list of products added to your Wishlist.']"));
	  HighlightElement(WishListContent);
	  Assert.assertTrue(WishListContent.isDisplayed());
	  
	  //Navigate to domain http://demo.guru99.com/v4/
	  OpenAnyURLWithJS("OpenAnyURLWithJS");
	  
	  String DemoPageDomain = (String) executeJSForBrowser("return document.domain;");
	  Assert.assertEquals(DemoPageDomain,"live.guru99.com");
	   
  }
  @Test
  public void TS02_RemoveAttribute() {
	  //Navigate to url : https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
	  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  
	  String firstName= "Automation", lastName= "Testing";
	  
	  WebElement iframeLogin = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
	  driver.switchTo().frame(iframeLogin);
	  
	  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(firstName);
	  
	  WebElement lastNameTextBox = driver.findElement(By.xpath("//input[@name='lname']"));
	  removeAnyAttributeInDOM(lastNameTextBox, "disabled");
	  lastNameTextBox.sendKeys(lastName);
	  
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  
	  String messDisplay = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
	  Assert.assertTrue(messDisplay.contains(firstName)&&messDisplay.contains(lastName));
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public void HighlightElement(WebElement element) {
	  	JavascriptExecutor js = (JavascriptExecutor) driver;
	  	js.executeScript("arguments[0].style.border='6px groove green'", element);
  }
  //Open URL
  public Object OpenAnyURLWithJS(String url) {
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  return js.executeScript("window.location = '" + url + "'");
  }
  
  //Execute for Browser
  public Object executeJSForBrowser(String javaSript) {
	  {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript(javaSript);
	  				} 
   }
  
  //Execute for WebElement
  public Object executeJSForWebElement(WebElement element) {
	  {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].click();", element);
	  	}
   }
  
  //Remove attribute in DOM
  public void removeAnyAttributeInDOM( WebElement element, String attributeName) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
          try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
          
  	}
  
  //Scroll to bottom page
  public Object scrollJSToBottomPage() {
	  {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	  					}
  	} 
}

