package rbscompletetest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class signup {

	public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");		 
	// Initialize browser
	WebDriver driver=new ChromeDriver();		 
	// Maximize browser		 
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	// Launch the application
	String baseUrl ="http://automationpractice.com";
	driver.get(baseUrl);
	driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/nav/div/a")).click();
	driver.findElement(By.xpath("//input[@id='email_create']")).clear();
	driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("Someonetest5@exampletest.com");
	driver.findElement(By.xpath("//button[@id='SubmitCreate']/span")).click();
	WebDriverWait wait=new WebDriverWait(driver, 20);
	WebElement SelectGenderlink;
	SelectGenderlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//input[@id='id_gender2']")));
	SelectGenderlink.click();
	driver.findElement(By.xpath("//input[@id='customer_firstname']")).clear();
	driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Minnie");
	driver.findElement(By.xpath("//input[@id='customer_lastname']")).clear();
	driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Mouse");
	driver.findElement(By.xpath("//input[@id='passwd']")).clear();
	driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Password123");
	driver.findElement(By.id("days")).sendKeys("1");
	driver.findElement(By.id("months")).sendKeys("January");
	driver.findElement(By.id("years")).sendKeys("1989");
	driver.findElement(By.id("address1")).sendKeys("Address1");
	driver.findElement(By.id("address2")).sendKeys("Address2");
	driver.findElement(By.id("city")).sendKeys("City1");
	driver.findElement(By.id("id_state")).sendKeys("California");
	driver.findElement(By.xpath("//input[@id='postcode']")).clear();
	driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("00123");
	driver.findElement(By.xpath("//input[@id='phone']")).clear();
	driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("0123456789");
	driver.findElement(By.xpath("//input[@id='phone_mobile']")).clear();
	driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("0123456789");
	driver.findElement(By.xpath("//div[4]/button/span")).click();
	driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?mylogout=']")).click();
	driver.close();
    System.exit(0);
}
	}
