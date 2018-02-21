package fabHotels;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class NewTest {
	WebDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void browserConection() {
		Proxy p = new Proxy();
		p.setProxyType(Proxy.ProxyType.DIRECT);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, p);
		driver = new FirefoxDriver(cap);
		driver.get("https://www.fabhotels.com/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void VerifyHomePage() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "FabHotels: India’s Best Budget Hotels", "Title not match.");
	}

	@Test(priority = 2)
	public void HotelSearch() throws Exception { // Search hotel location
		driver.findElement(By.id("autocomplete-location")).sendKeys("Delhi, India");

		// CheckIn date
		WebElement CheckIn = driver.findElement(By.xpath(".//*[@id='datepicker']/div[1]/div/input"));
		CheckIn.click();
		while (!driver.findElement(By.className("datepicker-switch")).getText().contains("March 2018")) {
			driver.findElement(By.className("next")).click();
		}

		List<WebElement> Arrival_m = driver.findElements(By.xpath("//td[@class=\"day\"]"));

		for (WebElement Arrival_dayPick : Arrival_m) {
			String ArrivalDay = Arrival_dayPick.getText();
			if (ArrivalDay.equalsIgnoreCase("20")) {
				Arrival_dayPick.click();
				break;
			}

		}

		// CheckOut date
		WebElement CheckOut = driver.findElement(By.xpath(".//*[@id='datepicker']/div[2]/div/input"));
		CheckOut.click();
		while (!driver.findElement(By.className("datepicker-switch")).getText().contains("March 2018")) {
			driver.findElement(By.className("next")).click();
		}

		List<WebElement> Deparuture_m = driver.findElements(By.xpath("//td[@class=\"day\"]"));

		for (WebElement Deparuture_dayPick : Deparuture_m) {
			String DeparutureDay = Deparuture_dayPick.getText();
			if (DeparutureDay.equalsIgnoreCase("24")) {
				Deparuture_dayPick.click();
				break;
			}

		}

		// Select adult
		driver.findElement(By.xpath(".//*[@class='wrap-select-box wrap_select_box']")).click();
		Thread.sleep(2000);

		List<WebElement> adults = driver.findElements(By.xpath("//div[@class='select-dropdown-section']/span"));
		for (WebElement adlt : adults) {
			String adultStr = adlt.getText();
			if (adultStr.equalsIgnoreCase("2")) {
				adlt.click();
				break;
			}
		}

		// Show Hotels
		driver.findElement(By.id("listingPageBtn")).submit();

	}

	@Test(priority = 3)
	public void SelectHotel() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.titleIs("Budget Hotels in New Delhi, Online Budget Hotel Booking in New Delhi - FabHotels"));
		String title = driver.getTitle();
		Assert.assertEquals(title, "Budget Hotels in New Delhi, Online Budget Hotel Booking in New Delhi - FabHotels");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='rupees-text']")).click();

		WebElement localcity = driver.findElement(By.xpath("//span[@class=\"locality-text\"]"));
		localcity.click();
		List<WebElement> localcities = driver.findElements(By.xpath("//li[@class=\"locality_filter_element\"]/label"));
		for (WebElement city : localcities) {
			String cityStr = city.getText();
			if (cityStr.equalsIgnoreCase("South Delhi")) {
				city.click();
				break;
			}
		}

		Thread.sleep(2000);
		List<WebElement> hotels = driver.findElements(By.xpath("//h3[@class=\"hotel-card-title\"]"));
		for (WebElement Bhotel : hotels) {
			String HName = Bhotel.getText();

			if (HName.equalsIgnoreCase("FabHotel Regalia")) {
				Bhotel.click();

				break;
			}
		}

		Thread.sleep(5000);

	}

	@Test(priority = 4)
	public void FinalPage() throws Exception {
	WebElement scrollTO, select_Guest, proceed_To_Pay;
		
		// Switch to new Tab
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String finalHotelURL = driver.getCurrentUrl();
		System.out.println(finalHotelURL);
		
		// Scroll Page down to Room list using Javascript Executor.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		scrollTO = driver.findElement(By.xpath("//div[contains(@class,'btn check-availability available')]"));
		System.out.println(scrollTO.getText());
		js.executeScript("arguments[0].scrollIntoView();", scrollTO);
		
		// jsEXE.executeScript("window.scrollBy(0,2000)");
		select_Guest = driver.findElement(By.xpath("//button[contains(@class,'btn select_room_type')]"));
		select_Guest.click();
		WebElement no_of_Guest = driver.findElement(By.xpath("//div[@class='select-box rooms_select']"));
		no_of_Guest.click();
		List<WebElement> select_no_Guest = driver.findElements(By.xpath("//div[@class='select-dropdown-wrap rooms_select_dropdown']/span"));
		for (WebElement Guest_Count : select_no_Guest) 
		{
			String GUESTNO = Guest_Count.getText();
			System.out.println("Select Room No" + GUESTNO);
			if (GUESTNO.equalsIgnoreCase("02")) {
				Guest_Count.click();
				break;
			}
		}
		proceed_To_Pay = driver.findElement(By.xpath("//button[contains(@class,'btn proceed_to_pay_button')]"));
		proceed_To_Pay.click();
		Thread.sleep(3000);
	}

	@AfterTest
	public void afterMethod() 
	{

		driver.quit();

	}

}
