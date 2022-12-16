package Test.Testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Take1MMT {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\uayus\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		Thread.sleep(5000);
		driver.switchTo().frame("notification-frame-~55854522");
		if(driver.findElement(By.xpath("//a[@class='close']")).isDisplayed()){
			driver.findElement(By.xpath("//a[@class='close']")).click();
		}
		driver.switchTo().defaultContent();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		driver.findElement(By.xpath("//input[@type='text' and @placeholder='From']")).sendKeys("Delhi");
		driver.findElement(By.xpath("//li[@role='option']/div/div/p[contains(text(),'Delhi')]")).click();
		driver.findElement(By.xpath("//label[@for='toCity']")).click();
		driver.findElement(By.xpath("//input[@type='text' and @placeholder='To']")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//div[contains(text(),'BOM')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//span[@class=\"bgProperties icon20 overlayCrossIcon\"]")).isDisplayed()){
			driver.findElement(By.xpath("//span[@class=\"bgProperties icon20 overlayCrossIcon\"]")).click();
		}
		driver.findElement(By.xpath("(//span[contains(text(),'Departure')])[2]")).click();
		Thread.sleep(3000);
		List<WebElement> allPrices = driver.findElements(By.xpath("//div[@class='listingCard']/div/div/div/div/p[contains(text(),'₹')]"));
		ArrayList<Integer> prices = new ArrayList<Integer>();
		for(WebElement price : allPrices) {
			String Strprice = price.getText().split(" ")[1];
			String[] StrpriceArr = Strprice.split(",");	//8,894
			int finalPrice = Integer.parseInt(StrpriceArr[0])*1000 + Integer.parseInt(StrpriceArr[1]);
			prices.add(finalPrice);
		}
		Collections.sort(prices);
		int secondLowestPrice = prices.get(1);
		System.out.println("second Lowest Price : " + secondLowestPrice);
		String priceForXpath = Integer.toString( secondLowestPrice%1000);
		String flightName = driver.findElement(By.xpath("(//div[@class='priceSection']/div/div/p[contains(text(),'"+priceForXpath+"')]//parent::div//parent::div//parent::div//preceding-sibling::div)[1]/div/p[1]")).getText();
		System.out.println("flight name : " +flightName);
		driver.quit();
				
				
				
		
		
		
	}

}
