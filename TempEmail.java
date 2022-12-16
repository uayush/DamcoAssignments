package Test.Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TempEmail {

	public static void main(String[] args) {
		//please provide valid username and password for gmail
		String emailId = "";
		String password = "";
		
		String subjectInput = "hello";
		String bodyinput = "hi";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\uayus\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://temp-mail.org/en/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-clipboard-action=\"copy\"])[2]")));
		driver.findElement(By.xpath("(//button[@data-clipboard-action=\"copy\"])[2]")).click();
		driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(emailId);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//div[@id=':1ab' and text()='Recipients']")).click();
		WebElement emailAddInput = driver.findElement(By.xpath("//input[@id=':1f9' and @role='combobox']"));
		emailAddInput.sendKeys(Keys.CONTROL+ "v");
		driver.findElement(By.xpath("//input[@name = 'subjectbox']")).sendKeys(subjectInput);
		driver.findElement(By.xpath("//div[@aria-label = 'Message Body']")).sendKeys(bodyinput);
		driver.findElement(By.xpath("//div[@role= 'button' and text()='Send']")).click();
		driver.navigate().back();
		WebElement emailpresent = driver.findElement(By.xpath("//a[text()='hello']"));			
		wait.until(ExpectedConditions.visibilityOf(emailpresent));
		emailpresent.click();
		String actualSubject = driver.findElement(By.xpath("//div[@class='user-data-subject']/h4")).getText();
		Assert.assertEquals(subjectInput, actualSubject);
		String actualBody = driver.findElement(By.xpath("//div[@class='inbox-data-content-intro']/p")).getText();
		Assert.assertEquals(bodyinput, actualBody);
	}
	

}
