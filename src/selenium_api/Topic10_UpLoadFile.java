package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Topic10_UpLoadFile {
	WebDriver driver;

	String projectDirectory = System.getProperty("user.dir");
	String fileName = "01.PNG";
	String uploadFilePath = projectDirectory + "\\image\\" + fileName;
	
	String chromeUpload = projectDirectory +"\\upload\\chrome.exe" ;
	String firefoxUpload = projectDirectory +"\\upload\\firefox.exe" ;
	String ieUpload = projectDirectory +"\\upload\\ie.exe" ;
	
	String folderName = "automation" + randomNumber();
	String EmailAddress ="ngoc" + randomNumber() + "@gmail.com";
	String firstName = "NgocPhan";
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//System.out.println("Test");		

	}

	//@Test
	public void TS01_UploadFileBySendkeys() throws Exception {
		//System.out.println("http://blueimp.github.io/jQuery-File-Upload/");
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(uploadFilePath);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='" + fileName + "']")).isDisplayed());

	}

	//@Test
	public void TS02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		//User Chrome/firefox
		WebElement UploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		
		//IE
		//WebElement UploadElement = driver.findElement(By.cssSelector("//span[contains(text(),'Add files...')]"));
		UploadElement.click();
		Thread.sleep(3000);
		
		Runtime.getRuntime().exec(new String[] { firefoxUpload, uploadFilePath });
		
		//Verify uploaded
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '" + fileName + "']")).isDisplayed());
		
	}

	//Should be run on FF and chrome 
	//@Test
	public void TS03_UploadByRobot() throws InterruptedException, Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//User Chrome/firefox
		WebElement UploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		UploadElement.click();
		Thread.sleep(3000);
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(uploadFilePath);

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		driver.findElement(By.className("fileinput-button")).click();

		Robot robot = new Robot();
		Thread.sleep(1000);
		//Focus vào Textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Gia lap control V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		//Nha phim Ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		//Nhan enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan phim xuong sau do tha ra

		//Verify uploaded
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title = '" + fileName + "']")).isDisplayed());
		
		
	}
	
	@Test
	public void TS03_UploadFile() {
		//Navigate to URL
		driver.get("https://encodable.com/uploaddemo/");
		
		driver.findElement(By.xpath("//input[@id='uploadname1']")).sendKeys(uploadFilePath);
		
		WebElement upLoadDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select select = new Select(upLoadDropdown);
		select.deselectByVisibleText("/uploaddemo/files/");
		
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folderName);
		
		//Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys();
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(firstName);
		
		//Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		//Step 07 - Verify information
	    // Email Address: ngoc@gmail.com/ First Name: NgocPhan
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='Email Address:" + EmailAddress +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='First Name:" + firstName + "']")).isDisplayed());

	    // File name: UploadFile.jpg
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dt[contains(text(),'File 1 of 1:')]/a[text()='" + fileName +"']")).isDisplayed());

		//Step 08 - Click 'View Uploaded Files' link
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		
		//Step 09 - Click to random folder (Ex: ngoc012345)
		driver.findElement(By.xpath("//a[text()='" + folderName + "']")).click();
		
		//Step 10 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName + "']")).isDisplayed());
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
		
	}

}
