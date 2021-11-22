package AxeTrading.Task;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestLogin {
	
	public WebDriver driver;
	String driverPath = ".//chromedriver.exe";
    String loginURL = "application login url";
    
    @DataProvider(name = "validLoginDetails")
	public Object[][] validLoginDetails() {
		return new Object[][] { 
			    { "shhubh1997@gmail.com", "myPassword"},
			    { "shubha19@axetrading.com", "password"}
		};
    }
    
    @DataProvider(name = "invalidLoginDetails")
	public Object[][] invalidLoginDetails() {
		return new Object[][] { 
			    { "shhubh19@gmail.com", "myPassword"},
			    { "invalidMail@gmail.com", "myppassword"}
		};
    }
    
	@BeforeSuite
    public void launchBrowserAndLoginPage() {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver= new ChromeDriver();
        driver.get(loginURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        acceptCookies();
    }
	
	public void acceptCookies() {
		// Accept cookies if it has to be accepted before performing login
		driver.findElement(By.xpath("//button[@data-cookiebanner='accept_button']")).click();
	}


	@Test(dataProvider = "validLoginDetails")
	public void testValidLoginTestCases(String username, String password) throws InterruptedException {
		// Perform login based on text fields and button id or xpath or name
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(3000);
        
        String expectedUrl = "url after login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        System.out.println("Successfully loggedin");   
	}
	
	@Test(dataProvider = "invalidLoginDetails")
	public void testInvalidLoginTestCases(String username, String password) throws InterruptedException {
		// Perform login based on text fields and button id or xpath or name
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(3000);
        
        String expectedUrl = "url after login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(actualUrl, expectedUrl);
	}

	@AfterTest
	public void logout() throws InterruptedException {
		// Add logout functionality based on logout button id or xpath
		if (!driver.findElements(By.id("logout")).isEmpty()) {
			driver.findElement(By.id("logout")).click();
			System.out.println("Successfully logged out");
		}
	}
	
	@AfterSuite
    public void closeBrowser(){
        driver.close();
    }

}
