package rbscompletetest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
//import org.openqa.selenium.*;

public class LogIn_PlaceOrder {

		public static void main(String[] args) {
			 
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");		 
			// Initialize browser
			WebDriver driver=new ChromeDriver();		 
			// Maximize browser		 
			driver.manage().window().maximize();
			// Launch the application
			String baseUrl ="http://automationpractice.com";
			//improvise sign on click then uncomment below code
			//String baseUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";     
			driver.get(baseUrl);
			//Click on Sign in 
			driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/nav/div/a")).click();
			//Key in Credentials
			driver.findElement(By.xpath("//div[2]/form/div/div/input")).sendKeys("Someonetest@exampletest.com");
			driver.findElement(By.xpath("//span/input")).sendKeys("Password123");
			//Click log in 
			driver.findElement(By.xpath("//p[2]/button/span")).click();
			
			//Update Personal Information
			//Click on Personal Information
			driver.findElement(By.xpath("//li[4]/a/span")).click();
			//update first name replacing the current one
			driver.findElement(By.xpath("//div[2]/input")).clear();
			driver.findElement(By.xpath("//div[2]/input")).sendKeys("SatyashreeUpdate");
			//change password
			driver.findElement(By.xpath("//div[6]/input")).sendKeys("Password123");
			driver.findElement(By.xpath("//div[7]/input")).sendKeys("Password123");
			driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Password123");
			//click submit
			driver.findElement(By.xpath("//div[@id='center_column']/div/form/fieldset/div[11]/button/span")).click();
			//order tshirt
			//Click for tshirt tab
			driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[3]/a")).click();
			//Select the specific Tshirt
			driver.findElement(By.cssSelector("a[title=\"Faded Short Sleeve T-shirts\"]")).click();
			//Select the Quick View Frame
			driver.switchTo().frame(0);
			// Select Quantity
			driver.findElement(By.xpath("//div[2]/p/input")).clear();
			driver.findElement(By.xpath("//div[2]/p/input")).sendKeys("2");
			//Select the Size
			driver.findElement(By.id("group_1")).sendKeys("S");
			//Select the color
			driver.findElement(By.xpath("//fieldset[2]/div/ul/li[2]/a")).click();
			//Add to Cart
			driver.findElement(By.xpath("//div[3]/div/p/button")).click();
			//click on proceed to checkout in the pop up
			WebDriverWait wait=new WebDriverWait(driver, 20);
			WebElement ProceedtoCheckoutlink;
			ProceedtoCheckoutlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//span[contains(.,'Proceed to checkout')]")));
			ProceedtoCheckoutlink.click();
			//Click on Proceed to Checkout in Summary
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a/span")).click();
			//Click on Proceed to Checkout in Address
			driver.findElement(By.xpath("//div[@id='center_column']/form/p/button/span")).click();
			//Click on Proceed to Checkout in Shipping
			// Confirm Terms and Service
			driver.findElement(By.xpath("//input[@id='cgv']")).click();
			//Then Proceed to Checkout
			driver.findElement(By.xpath("//form[@id='form']/p/button/span")).click();
			//click on Pay by bankwire
			driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']/div/div/p/a")).click();
			//click on confirm my order
			driver.findElement(By.xpath("//p/button/span")).click();
			//Get the Order Id
			String OrderId = driver.findElement(By.xpath("//div[@id='center_column']/div")).getText().substring(216,225);
			//Go to Order History & get the Order Id
			driver.findElement(By.xpath("//div[@id='center_column']/p/a")).click();
			String HisOrderId = driver.findElement(By.xpath("//table[@id='order-list']/tbody/tr/td")).getText();
			//Verify Order in Order history
			System.out.println(OrderId);
			System.out.println(HisOrderId);
			if (OrderId.equals(HisOrderId)){
				System.out.println("Order has been placed Successfully.");
			}
			else {
				System.out.println("Order is not available in Order history.");
			}
			driver.close();
		    System.exit(0);
		    
		}
	}
