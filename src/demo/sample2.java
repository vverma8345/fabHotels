package demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class sample2 
{ 
	
	WebDriver driver = null;
	
	  @BeforeMethod
	  public void beforeMethod() 
	  {
		  driver = new FirefoxDriver();
		  driver.get("http://ct.uccpreconfig.fastbooking.ch/index.php?Logout=1");
		  driver.manage().window().maximize();
	  }

	@Parameters({"username","password"})
	@Test
	public void ValidCredentails(String username,String password) throws Exception 
	{
		login(username,password);
		
	}
	
	
	
	public void login(String Username,String Password) throws Exception
	{
		driver.findElement(By.id("txtLoginID")).sendKeys(Username);
		driver.findElement(By.id("pwdKey")).sendKeys(Password);
		WebElement tools = driver.findElement(By.id("tool"));
		Select slt = new Select(tools);
		slt.selectByVisibleText("Checker");
		driver.findElement(By.id("btnLogin")).submit();

		/* HomePage Elements */
		Thread.sleep(2000);
		WebElement homePage = driver.findElement(By.xpath("//th[@class=\"blackLink\"]"));
		String Value = homePage.getText();
		Assert.assertEquals("Welcome to Checker Vimal Verma", Value, "Test Case Failed.");
		System.out.println("Test Case Passed.");

	}

  
  @AfterMethod
  public void afterMethod() throws Exception 
  {
	  driver.quit();
	  
  }


}
