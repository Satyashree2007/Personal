package rbscompletetest;

//import java.util.List;
import java.util.Random;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class signup {

	public static String[] Signup(WebDriver driver) {
		
	String[] eMail_PWD = new String[2];

	//Click on Login
	driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/nav/div/a")).click();
	
	//Clear the existing Add account email value if any
	driver.findElement(By.xpath("//input[@id='email_create']")).clear();
	
	//Generate random email id and submit for account creation
	String randomEmail = randomEmail();
	driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(randomEmail);
	driver.findElement(By.xpath("//button[@id='SubmitCreate']/span")).click();
	
	// If the email Id is already present then system throws an error, in that case create another random email Id
	Boolean isAccountErrorPresent = driver.findElements(By.id("create_account_error")).size()>0;
	
	//Repeat until email id is accepted
	 while(isAccountErrorPresent) {
		WebDriverWait wait=new WebDriverWait(driver, 60);
		Boolean isAccountErrorDisplayed = driver.findElement(By.id("create_account_error")).isDisplayed();
		
		// If Email Id not accepted then go for next email id
		if(isAccountErrorDisplayed) {
			System.out.println(randomEmail +" was already there hence trying new id");
			randomEmail = randomEmail();
			driver.findElement(By.xpath("//input[@id='email_create']")).clear();
			driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(randomEmail);
		}else {
			driver.findElement(By.xpath("//button[@id='SubmitCreate']/span")).click();
			
			//if Email Id is accepted the first field of next page is Gender
			WebElement GenderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"uniform-id_gender1\"]")));
			GenderLink.click();
		};
		isAccountErrorPresent = driver.findElements(By.id("create_account_error")).size()>0;
	};
	
	// Name 
	driver.findElement(By.xpath("//input[@id='customer_firstname']")).clear();
	driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Minnie");
	driver.findElement(By.xpath("//input[@id='customer_lastname']")).clear();
	driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Mouse");
	
	// Password
	String randomPWD = randomPWD();
	driver.findElement(By.xpath("//input[@id='passwd']")).clear();
	driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(randomPWD);
	
	// D.O.B
	driver.findElement(By.id("days")).sendKeys("1");
	driver.findElement(By.id("months")).sendKeys("January");
	driver.findElement(By.id("years")).sendKeys("1989");
	
	//Address
	driver.findElement(By.id("address1")).sendKeys("Address1");
	driver.findElement(By.id("address2")).sendKeys("Address2");
	driver.findElement(By.id("city")).sendKeys("City1");
	driver.findElement(By.id("id_state")).sendKeys("California");
	driver.findElement(By.xpath("//input[@id='postcode']")).clear();
	driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("00123");
	
	//Contacts
	driver.findElement(By.xpath("//input[@id='phone']")).clear();
	driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("0123456789");
	driver.findElement(By.xpath("//input[@id='phone_mobile']")).clear();
	driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("0123456789");
	
	//Submit for Account Creation
	driver.findElement(By.xpath("//div[4]/button/span")).click();
	
	//There is no successful message after submit for signup. But on Successful account creation system returns to HomePage
	WebDriverWait wait=new WebDriverWait(driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//p[contains(.,'Welcome to your account. Here you can manage all of your personal information and orders.')]")));
	Boolean isAccountCreated = driver.findElements(By.xpath("//p[contains(.,'Welcome to your account. Here you can manage all of your personal information and orders.')]")).size()>0;
	
	//So if HomePage appears then Account has been created
	if(isAccountCreated) {
	System.out.println("Account has been created for '"+randomEmail+"' with password as '"+randomPWD+"'");
	}
	else {
		System.out.println("Some Issue with Account creation for emailid '"+randomEmail+"'. Hence closing the browser. Please try again");
		driver.close();
	};
	
	//Logout
	driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?mylogout=']")).click();	
	
	//Return EmailId
	eMail_PWD[0]= randomEmail; 
	eMail_PWD[1]= randomPWD; 
	return eMail_PWD;
}
	private static String randomEmail() {
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(); 
		return "Someone"+ randomInt +"@example.com";
    }
	private static String randomPWD() {
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000); 
		return "Password"+ randomInt;
    }
	}
