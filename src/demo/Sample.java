package demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Sample 
{

	
	public static void main(String[] args) throws Exception 
	{
		/* LoginPage Elements */
		WebDriver driver = new FirefoxDriver();
		driver.get("http://ct.uccpreconfig.fastbooking.ch/index.php?Logout=1");
		driver.findElement(By.id("txtLoginID")).sendKeys("vverma");
		driver.findElement(By.id("pwdKey")).sendKeys("123456");
		WebElement tools = driver.findElement(By.id("tool"));
		Select slt = new  Select(tools);
		slt.selectByVisibleText("Checker");
		driver.findElement(By.id("btnLogin")).submit();
		
		/* HomePage Elements */
		Thread.sleep(2000);
		WebElement homePage = driver.findElement(By.xpath("//th[@class=\"blackLink\"]"));
		String Value = homePage.getText();
		Assert.assertEquals("Welcome to Checker Vimal Verma",Value,"Test Case Failed.");
		System.out.println("Test Case Passed.");
		driver.close();
		
	}	
	
}
