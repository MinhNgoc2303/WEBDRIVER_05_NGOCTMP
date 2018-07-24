package selenium_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
//import org.junit.rules.Timeout;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterClass;

public class Topic_02_XpathCss {
	WebDriver driver;
	
	@BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  
	  }
	
	
	@Test
	public void TC_01_VerifyURLandtitle() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/");
		
		String title = driver.findElement(By.xpath("//title[text()='Home page']")).getText();
		Assert.assertEquals(title, "Home page");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		 driver.navigate().back();
		 
		 String ActualtextLogin = driver.findElement(By.linkText("http://live.guru99.com/index.php/customer/account/login/")).getText();
		 Assert.assertEquals(ActualtextLogin, "http://live.guru99.com/index.php/customer/account/login/" );

		 driver.navigate().forward();
		 
		 String ActualtextCreate = driver.findElement(By.linkText("http://live.guru99.com/index.php/customer/account/create/")).getText();
		 Assert.assertEquals(ActualtextCreate, "http://live.guru99.com/index.php/customer/account/create/" );
		  
	  }
	
	
	@Test
	public void TC_02_LoginEmpty() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("send2")).click();
		
		String ErrorMess_username = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(ErrorMess_username, "This is a required field.");
		
		String ErrorMess_Password = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(ErrorMess_Password, "This is a required field.");
		  
	  }
	  

	@Test
	public void TC_03_LoginWithEmailInvalid() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		
		String ErrorMessInvalidEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(ErrorMessInvalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");
		
		  
	  }
	  
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("#pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		
		String ErrorMessInvalidPass = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(ErrorMessInvalidPass, "Please enter 6 or more characters without leading or trailing spaces.");
		  
	  }
	
	@Test
	public void TC_05_CreateAnAccount() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Phan");
		driver.findElement(By.id("lastname")).sendKeys("Alice");
		driver.findElement(By.id("email_address")).sendKeys("automation" + RanDomEmail() + "@domain.com");
		driver.findElement(By.id("password")).sendKeys("NgocCute@123");
		driver.findElement(By.id("confirmation")).sendKeys("NgocCute@123");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String MessVerify = driver.findElement(By.xpath("//span[contains(.,'Thank you for registering with Main Website Store.')]")).getText();
		Assert.assertEquals(MessVerify, "Thank you for registering with Main Website Store.");
		
		//Step 06 - LogoutKhoiheThong
		driver.findElement(By.xpath("//span[@class='label'and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		//Step 07 - KiemTraheThongLoadVeHomepageSaukhiLogoutThanhCong
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='page-title']/h2[contains(text(),'This is demo site for')]"));
		
		driver.close();
		
		
		}
		
		public int RanDomEmail() {
		
			Random random= new Random();
			int number = random.nextInt(999999);
			return number;
			
		}
		

  @AfterClass
  public void afterClass() {
  }

}
