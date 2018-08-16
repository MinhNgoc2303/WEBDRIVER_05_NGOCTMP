package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_HandleCustomDropdowList_Part2 {
	WebDriver driver;
	WebDriver wait;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		//driver = (WebDriver) new WebDriverWait (driver,30);
		
		System.setProperty("webdriver.chrome.driver", "\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = (WebDriver) new WebDriverWait(driver, 30);
		
	  	}
	
	@Test
	public void TC_02_cont() {
		//Jquery
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", "Faster");
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text'and text()='Faster']")).isDisplayed());
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text'and text()='19']")).isDisplayed());
		
		//Angular
		driver.get("https://material.angular.io/components/select/examples");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", "Faster");
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text'and text()='Faster']")).isDisplayed());
		
		selectCustomDropdownList("//mat-select[@placeholder='State']", "//mat-option/span", "Washington");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Washington']")).isDisplayed());
		
		//kendoUI
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		selectCustomDropdownList("//span[@aria-lableledby='color_lable']", "//ul[@id='color_listbox']/li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-lablledby='color_lable']//span[@class='k-input' and text()='Grey']/parent::span")).isDisplayed());
		
		
		//VueJSS
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		selectCustomDropdownList("//div[@class='btn-group']/ul", "//div[@class='btn-group']/ul/li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(), 'Third Option')]")).isDisplayed());
		
		public void selectCustomDropdownList(String dropdown, String listItems, String valueItems) {
			//Click va2o dropdown
			WebElement dropdownList = driver.findElement(By.xpath(dropdown));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownList);
			dropdownList.click();
			
			//Get tat ca item trong dropdown vao 1 list element
			List<WebElenment> allItems = driver.findElements(By.xpath(listItems));
			
			//Wait để tất cả phần tử trong dropdown được hiển thị
			wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
			
			//Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
			for (WebElement item : allItems) {
				//if (item.getText().trim().equals(valueItems)) dùng trim nếu như có khoảng trắng or tab ở trước và sau text -> clear space đó đi 
				if (item.getText().equals(valueItems)) {
					//Scroll to item
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
					Thread.sleep(3000);
					//Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp
					item.click();
					break;
				}
			}
		}
		
	}
 

	@AfterClass
	public void afterClass() {
		driver.quit();
		}

}