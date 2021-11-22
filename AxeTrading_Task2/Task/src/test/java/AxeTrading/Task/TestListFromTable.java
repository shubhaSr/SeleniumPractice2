package AxeTrading.Task;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

class TradeRecord {
	public String description, coupon, rating, currentPrice, action;
	
	TradeRecord(String description, String coupon, String rating, String currentPrice, String action) {
		this.description = description;
		this.coupon = coupon;
		this.rating = rating;
		this.currentPrice = currentPrice;
		this.action = action;
	}
	
	void printRecord() {
		System.out.println("{description: " + this.description + 
							", coupon: " + this.coupon + 
							", rating: " + this.rating + 
							", current price: " + this.currentPrice + 
							", action: " + this.action+ "}");
	}
}

public class TestListFromTable {

	public WebDriver driver;
	String driverPath = ".//chromedriver.exe";
    String blotterUrl = "blotter url";
    String xPathForTable = ".//*[@id=\"leftcontainer\"]/table";
    
	@BeforeSuite
    public void launchBrowserAndGoToTableURL() {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver= new ChromeDriver();
        driver.get(blotterUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

	@Test
    public void testListFromBlotterTable() {	
        WebElement blotterTable = driver.findElement(By.xpath(xPathForTable));
        List<WebElement> table_rows = blotterTable.findElements(By.tagName("tr"));
        int rows_count = table_rows.size();
        	
        List<TradeRecord> tradeRecordList = new ArrayList<TradeRecord>();
        	
        for (int row = 0; row < rows_count; row++) {
        	List<WebElement> row_data = table_rows.get(row).findElements(By.tagName("td"));
        	if (row_data.size() != 0) {
        		String description = row_data.get(0).getText();
        		String coupon = row_data.get(1).getText();
        		String rating = row_data.get(2).getText();
        		String currentPrice = row_data.get(3).getText();
        		String action = row_data.get(4).getText();
        		
        		TradeRecord tradeRecord = new TradeRecord(description, coupon, rating, currentPrice, action);
        		tradeRecordList.add(tradeRecord);
        	}
		}
    	    
    	for (TradeRecord tradeRecord : tradeRecordList) {
    		// Assertions such as no of records, trade record data can be added based on requirements
    	    tradeRecord.printRecord();
		}
		
	}
	
	@AfterSuite
    public void closeBrowser(){
        driver.quit();
    }	
    
}