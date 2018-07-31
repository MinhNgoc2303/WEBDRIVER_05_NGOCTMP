package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_TextArea_DropdownList {
	WebDriver driver; 
	String name, dob, address, city, state, pin, phone, mail, password, customerID = null, newAddress, newCity ;
	
	//BY ELEMENT VARIABLE
	By nameTextBox =  By.xpath("//input[@name='name']");
	By genderTextBox =  By.xpath("//input[@name='gender']");
	By DobTextBox =  By.xpath("//input[@name='dob']");
	By AddressTextarea =  By.xpath("//textarea[@name='addr']");
	By CityTextBox =  By.xpath("//input[@name='city']");
	By StateTextBox =  By.xpath("//input[@name='state']");
	By pinTextBox =  By.xpath("//input[@name='pinno']");
	By phoneTextBox =   By.xpath("//input[@name='telephoneno']");
	By mailTextBox =   By.xpath("//input[@name='emailid']");
	By passTextBox =   By.xpath("//input[@name='password']");
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  //DATA TEST
	  name = "Automation Test";
	  // do doan nay co bug, nen em phai truyen vao cho no sai moi chay dc: 1994-01-02
	  dob = "1994-01-02";
	  address = "123 Hoa Lu";
	  city = "Thong Nhat";
	  state = "Dong Nai";
	  pin = "123123";
	  phone = "1234567890";
	  mail = "auto" + randomUniqueNumber() + "@gmail.com";
	  password = "123456";
	  newAddress = "456 Ky Dong";
	  newCity = "Binh Duong";
	  
	  
  }
  
  
  @Test
  public void TC_01_HandleDropdownList() throws Exception {
	  driver.get("http://daominhdam.890m.com/");
	  Select JobRole01 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

	  //Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	  Assert.assertTrue(!JobRole01.isMultiple());
	  
	  //Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
	  JobRole01.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Automation Tester");
	  Thread.sleep(3000);
	  
	  //Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
	  JobRole01.selectByValue("manual");
	  Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(),"Manual Tester" );
	  Thread.sleep(3000);
	  
	  //Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
	  JobRole01.selectByIndex(3);
	  Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(),"Mobile Tester");
	  Thread.sleep(3000);
	  
	  //Step 09 - Kiểm tra dropdown có đủ 5 giá trị
	  int JobItems =JobRole01.getOptions().size();
	  Assert.assertEquals(JobItems, 5);
	  Thread.sleep(3000);
	  
  }
  
  @Test
  public void TC_03_HanldeTextbox_TextArea() throws InterruptedException {
	  //Step 01 - Access vào trang: http://demo.guru99.com/v4
	  driver.get("http://demo.guru99.com/v4");
	  
	  //Step 02 - Đăng nhập với thông tin: User = mngr146001  | Pass = qyzetUp
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr146001");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qyzetUp");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  //Verify HomePage được hiển thị thành công
	  //Assert.assertEquals(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")), "Welcome To Manager's Page of Guru99 Bank");
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  
	  //Step 03 - Chọn menu New Customer
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  
	  
	  //INPUT DATA NEW CUSTOMER
	  driver.findElement(nameTextBox).sendKeys(name);
	  driver.findElement(DobTextBox).sendKeys(dob);
	  driver.findElement(AddressTextarea).sendKeys(address);
	  driver.findElement(CityTextBox).sendKeys(city);
	  driver.findElement(StateTextBox).sendKeys(state);
	  driver.findElement(pinTextBox).sendKeys(pin);
	  driver.findElement(phoneTextBox).sendKeys(phone);
	  driver.findElement(mailTextBox).sendKeys(mail);
	  driver.findElement(passTextBox).sendKeys(password);
	  
	  Thread.sleep(5000);
	  
	  //Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  // Get dynamic customID
	  customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  
	  //Verify infor of Customer ID
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone );
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),mail );
	  
	  //Step 06 - Chọn menu Edit Customer > Nhập Customer ID > Submit
	  driver.findElement(By.xpath("//a[text()='EditCustomer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("customerID");
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  
	  //Check 3 fields : name, gender, DOB are disable
	  Assert.assertTrue(driver.findElement(nameTextBox).getAttribute("Disable").equals("On"));
	  Assert.assertTrue(driver.findElement(genderTextBox).getAttribute("Disable").equals("On"));
	  Assert.assertTrue(driver.findElement(DobTextBox).getAttribute("Disable").equals("On"));
	  
	  //Verify Customer and Address fields equals input datas
	  Assert.assertEquals(driver.findElement(nameTextBox).getAttribute("value"), name);
	  Assert.assertEquals(driver.findElement(AddressTextarea).getText(), address);
	  
	  //Edit data for address and city fields
	  driver.findElement(AddressTextarea).sendKeys("newAddress");
	  driver.findElement(CityTextBox).sendKeys("newCity");
	  
	  //Verify edit customer/address with new data
	  Assert.assertEquals(driver.findElement(By.xpath("//td[Text()='Address']/following-sibling::td")).getText(), newAddress);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[Text()='City']/following-sibling::td")).getText(), newCity);
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public int randomUniqueNumber() {
	  Random rand = new Random();
	  int number = rand.nextInt(999999)+1;
	  return number;
	  
  }
}
