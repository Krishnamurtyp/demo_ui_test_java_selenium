package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {

	private String actualTitle;
	
	public LoginPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="txtUsername")
	private WebElement usrNameEdt;	
	
	@FindBy(id="txtPassword")
	private WebElement passwordEdt;	
	
	@FindBy(id="btnLogin")
	private WebElement loginBtn;	
	
	public void loginToApp(String usr, String pwd) {
		usrNameEdt.sendKeys(usr);
		passwordEdt.sendKeys(pwd);
		loginBtn.click();
	}
	
	public String gettitle(WebDriver driver) {
		actualTitle = driver.getTitle();
		return actualTitle;
	}
	
	
}