package demo;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToolsQA {

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub

		 WebDriver driver = new FirefoxDriver();
		driver.get("https://www.fabhotels.com/search?locationsearch=Delhi%2C+India&checkIn=20+Mar+2018&checkOut=24+Mar+2018&guests=2&location_text=Delhi%2C+India&locality_text=Delhi&location_list=28.6618976%2C77.22739580000007&city=Delhi&nearcity=Delhi&radius=");
		
	
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[@class=\"rupees-text\"]")));
		act.build().perform();
		Thread.sleep(10000);
		
		
		
	
		
		
	}

}
