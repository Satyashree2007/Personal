package rbscompletetest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import rbscompletetest.signup;

public class LogIn_PlaceOrder {

		public static void main(String[] args) {
			
			// Point the web driver to local Chrome driver
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");	
			
			// Initialize browser
			WebDriver driver=new ChromeDriver();
			
			// Maximize browser		 
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			
			// Launch the application
			String baseUrl ="http://automationpractice.com";
			
			//Direct sign in page then uncomment below code and comment above
			//String baseUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";  
			
			driver.get(baseUrl);
			
			//Create Account
			String[] userName_PWD = signup.Signup(driver);
			String userName = userName_PWD[0];
			String PWD = userName_PWD[1];
			
			//Click on Sign in 
			driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/nav/div/a")).click();
			
			//Key in Credentials
			driver.findElement(By.xpath("//div[2]/form/div/div/input")).sendKeys(userName);
			driver.findElement(By.xpath("//span/input")).sendKeys(PWD);
			
			//and Click log in 
			driver.findElement(By.xpath("//p[2]/button/span")).click();
			
			//**********************Update Personal Information STARTs*******************************
			
			//Click on Personal Information link
			driver.findElement(By.xpath("//li[4]/a/span")).click();
			
			//update first name replacing the current one
			String oldName = driver.findElement(By.xpath("//div[2]/input")).getAttribute("value");
			driver.findElement(By.xpath("//div[2]/input")).clear();
			driver.findElement(By.xpath("//div[2]/input")).sendKeys("MickeyandMinnie");
			String newName = driver.findElement(By.xpath("//div[2]/input")).getAttribute("value");
			
			//change password
			driver.findElement(By.xpath("//div[6]/input")).sendKeys(PWD);
			driver.findElement(By.xpath("//div[7]/input")).sendKeys(PWD);
			driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(PWD);
			
			//click submit
			driver.findElement(By.xpath("//div[@id='center_column']/div/form/fieldset/div[11]/button/span")).click();
			
			// Error after save
			Boolean isErrorSaveName = driver.findElements(By.xpath("//p[contains(.,' Your personal information has been successfully updated.')]")).size()>0;
			if(isErrorSaveName) {
				Boolean isErrorSaveNameDisplayed = driver.findElement(By.xpath("//p[contains(.,' Your personal information has been successfully updated.')]")).isDisplayed();
				if (isErrorSaveNameDisplayed) {
					System.out.println("First Name has been updated from '"+ oldName +"' to '"+ newName + "'");
				};
			}
			else {
				System.out.println("There was some error while updating the first name. Please try again");
			};
			
			
			//**********************Update Personal Information ENDs*******************************
			
			
			//*********************Place an Order for T-shirt STARTs******************************************
			
			//Click for tshirt tab
			driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[3]/a")).click();
			
			//Select the specific Tshirt
			driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img")).click();
						
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
			
			//click on proceed to checkout on the pop up menu
			WebDriverWait wait=new WebDriverWait(driver, 30);
			WebElement ProceedtoCheckoutlink;
			ProceedtoCheckoutlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));
			ProceedtoCheckoutlink.click();
			
			//Click on Proceed to Checkout in Summary tab
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a/span")).click();
			
			//Click on Proceed to Checkout in Address tab
			driver.findElement(By.xpath("//div[@id='center_column']/form/p/button/span")).click();
			
			//Click on Proceed to Checkout in Shipping tab
			// Confirm Terms and Service
			driver.findElement(By.xpath("//input[@id='cgv']")).click();
			
			//Final Proceed to Checkout
			driver.findElement(By.xpath("//form[@id='form']/p/button/span")).click();
			
			//click on Pay by bankwire
			driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']/div/div/p/a")).click();
			
			//Confirm order
			driver.findElement(By.xpath("//p/button/span")).click();
			
			//Get the Order Id from the invoice details
			String OrderId = driver.findElement(By.xpath("//div[@id='center_column']/div")).getText().substring(216,225);
			
			//Go to Order History
			driver.findElement(By.xpath("//div[@id='center_column']/p/a")).click();
			
			//Find the number orders in Order History
			int rowNum = driver.findElements(By.xpath("//*[@id=\"order-list\"]/tbody/tr")).size();
			
			//Order Id is available on 1st Column
			int colNum = 1;
			
			//Set a flag for use
			boolean orderPlaced = false;
			
			//Loop through Order history
			WebElement BooksTable = driver.findElement(By.xpath("//*[@id=\"order-list\"]"));
		    List<WebElement> rowVals = BooksTable.findElements(By.tagName("tr"));
			for(int i=1; i<=rowNum; i++){
		        List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
				for(int j=0; j<=colNum; j++){
		        		String HisOrderId = colVals.get(j).getText();
		        		
		        		//Match the Order id and confirm if Order has been placed successfully
		        		if (OrderId.equals(HisOrderId)){
		    				System.out.println("Order has been placed successfully with OrderId '"+HisOrderId+"'");
		    				orderPlaced = true;
		    			}
		        	}
		        }
			
			// Order id did not match in entire Order History
			if (!orderPlaced) {
				System.out.println("Order is not available in Order history. Please try again placing the order");
			}
			
			//Close the Browser
			driver.close();
			
			// Close down the system variable
		    System.exit(0);
		    
		}
	}
