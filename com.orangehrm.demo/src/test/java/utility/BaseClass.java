package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pages.HomePage;
import pages.LoginPage;

public class BaseClass {
	
	private static WebDriver driver;
	final static String chromeProperty="webdriver.chrome.driver";
	final static String chromeDriverPath="C:\\chromedriver.exe";

		
	public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        System.setProperty(chromeProperty,chromeDriverPath);
		driver = new ChromeDriver(); 
		return driver;
	}
	
    public static void getUrl(String url) {
    	BaseClass.getDriver().get(url);
    	BaseClass.getDriver().manage().window().maximize();
    	BaseClass.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
