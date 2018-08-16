package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_UserInteractions {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  //@Test
  public void TS01_HovermouseElement_01() throws Exception {
	  driver.get("http://daominhdam.890m.com/");
	  
	  WebElement HoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
	  Actions action = new Actions(driver);
	  
	  //Hover mouse
	  action.moveToElement(HoverText).perform();
	  Thread.sleep(3000);
	  
	  //Verify tooltip is displayed
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(),"Hooray!");
	  
  }
  
  
  //@Test
  public void TS01_HovermouseElement_02() {
	  driver.get("https://www.myntra.com/");
	  
	  WebElement RightClick = driver.findElement(By.xpath(""));
	  Actions action = new Actions(driver);
	 
	  //Hover mouse
	  action.contextClick(RightClick);
	  
	  //
	  
	  
	  
	  
  }
  
  //@Test
  public void TS02_ClickandHoldElement() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> selectable = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
	  Actions action = new Actions(driver);
	  action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().perform();
	  
	  List<WebElement> selectableIsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(selectableIsSelected.size(), 4);
	  
	  
	  //Gia lap va bam phim Control
//	  List<WebElement> elements = driver.findElements(By.xpath("//*[@id='selectable']/li"));
//	  Actions action = new Actions(driver);
//	  action.keyDown(Keys.CONTROL).build().perform();
//	  
//	  elements.get(0).click();
//	  elements.get(2).click();
//	  elements.get(4).click();
//	  elements.get(6).click();
//	  elements.get(9).click();
	  
	  //Gia lap va nha phim Control ra 
	  //action.keyUp(Keys.CONTROL).build().perform();
	  

//	  List<WebElement> selectableIsSelected = driver.findElements(By.xpath("ui-state-default ui-selectee ui-selected"));
//	  Assert.assertEquals(selectableIsSelected.size(), 5);
	  
  }
  
  //@Test
  public void TS03_doubleClick() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement DoubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  
	  Actions action = new Actions(driver);
	  //Gia lap hanh dong Double click
	  action.doubleClick(DoubleClick).perform();
	  //Alert displayed
	  Alert alert = driver.switchTo().alert();
	  //Verify mess of alert
	  Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
	  //accept alert
	  alert.accept();
	  
	  
  }
  
  //@Test
  public void TS04_RightClickElement() {
	  //Navigate to url
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

	  WebElement rightclick = driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action = new Actions (driver);
	  
	  //Right click
	  action.contextClick(rightclick).perform();
	  
	  WebElement quitBefore = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
	  
	  //Hover to Quit
	  action.moveToElement(quitBefore).perform();
	  
	  //Verify hover success
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());
	  
	  //Click to quit
	  action.click(quitBefore).perform();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "clicked: quit");
	  alert.accept();
	  
  }
  
  //@Test
  public void TS05_DragAndDropElement_TC01() {
	  //navigate to url
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
	  //Drag and drop small circle in big circle
	  WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
	  
	  Actions action = new Actions (driver);
	  action.dragAndDrop(sourceElement, targetElement).perform();
	  
	  //Verify message has been changed
	  Assert.assertEquals(targetElement.getText(), "You did great!");
	  
  }

  
  @Test
  public void TS06_DragAndDropElement_TC02() {
	  //navigate to url
	  driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	  //Drag and drop small circle in big circle
	  WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
	  Assert.assertEquals(targetElement.getText(),"Drop here");
	  
	  Actions action = new Actions (driver);
	  action.dragAndDrop(sourceElement, targetElement).perform();
	  
	  //Verify message has been changed
	  Assert.assertEquals(targetElement.getText(), "Dropped!");
	  
  }

  
  //@Test
  public void TS06_DragAndDropElement_HMLM5() {
	  //navigate to url
	  driver.get("https://html5demos.com/drag/");
	  //Drag and drop small circle in big circle
	  WebElement sourceElement = driver.findElement(By.xpath("//div[@id='one']"));
	  WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
	
	  
	  Actions action = new Actions (driver);
	  action.clickAndHold(sourceElement).moveToElement(targetElement).release().perform();
	  
	  //Verify message has been changed
	 Assert.assertFalse(sourceElement.isDisplayed());
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
