package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic10_UpLoadFile {
	WebDriver driver;
	
	String projectDirectory = System.getProperty("user.dir");
	String fileName = "01.png";
	String uploadFilePath = projectDirectory + "\\image\\" + fileName;
	
  
  @BeforeClass
  public void beforeClass() {
	  //System.setProperty("", value)
	  //driver = new ChromeDriver();
	  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  System.out.println("Test");
	 
  }
  
  @Test
  public void TS01_UploadFileBySendkeys() throws Exception {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	  
	  WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
	  uploadElement.sendKeys(uploadFilePath);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()=' " + fileName + "']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
	  
	  Thread.sleep(3000);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()=' \" + fileName + \"']")).isDisplayed());
	  
	  
	  
	  
  }
  
  @Test
  public void f2() {
  }
  
  @Test
  public void f3() {
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
