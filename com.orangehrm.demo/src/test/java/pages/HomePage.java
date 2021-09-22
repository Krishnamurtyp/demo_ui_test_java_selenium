package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {


	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using="welcome")	
	private WebElement welcomeMsg;	
	
	@FindBy(how = How.XPATH, using="//h1[text()='Dashboard']")	
	private WebElement dashBoardLabel;	
	
	@FindBy(how = How.XPATH, using="//a[text()='Logout']")
	private WebElement logOutBtn;	
	
	public WebElement getWelcomeMsg() {
		
		return welcomeMsg;
		
	}
	
	public WebElement getdashBoardLabel() {
		
		return dashBoardLabel;
		
	}
		
	public void logOutFromApp() {
			if(welcomeMsg.isDisplayed() && welcomeMsg.isEnabled()) {
				welcomeMsg.click();
				logOutBtn.click();
			}
			
	}
	
}
