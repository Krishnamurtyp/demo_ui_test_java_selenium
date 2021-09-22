package testcases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


import pages.HomePage;
import pages.LoginPage;
import utility.BaseClass;
import utility.ExcelUtility;
import utility.ReadExcelFile;
import utility.Report;

public class Login_Test extends BaseClass {
	
	final String expectedTitle = "OrangeHRM";
	File file;
	FileReader rd;
	BufferedReader br;
	String line;
	String usrName;
	String passWord;
	LoginPage loginPage; 
	HomePage homePage;
	final static String filePath="C:\\ui_test\\com.orangehrm.demo\\testData\\Users.txt";
	final static String filePathExecutionStatus="C:\\ui_test\\com.orangehrm.demo\\testData\\status.xlsx";
	final static String expectedHomePageURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
	final static String expectedURLonInvalidCred="https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials";
	final static String excelPath="C:\\ui_test\\com.orangehrm.demo\\testData\\Users.xlsx";
	final static String URL="https://opensource-demo.orangehrmlive.com/";
	
    @BeforeClass
    public void setUp() {
    	loginPage = new LoginPage(BaseClass.getDriver());
    	homePage = new HomePage(BaseClass.getDriver());
        BaseClass.getUrl(URL);
    }

		
	@Test(testName="Login using valid cred",enabled=true, groups= {"Smoke Test"}, priority=1)
	public void test_single_user() {
		Report.startReport("C:\\ui_test\\com.orangehrm.demo\\Reports\\", "Test Login Functionality", "Login With Valid UserName and Password");
		String loginPageTitle = loginPage.gettitle(BaseClass.getDriver());
		if(expectedTitle.equalsIgnoreCase(loginPageTitle)) {
			Assert.assertEquals(loginPageTitle, expectedTitle);
			loginPage.loginToApp("Admin", "admin123");
			if(homePage.getWelcomeMsg().isDisplayed() && homePage.getdashBoardLabel().isDisplayed()) {
				ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 1, 1, "Passed");
				Report.reportLog(LogStatus.PASS, "User Logged in Successfully");
				homePage.logOutFromApp();
			}
		}else {
			Assert.assertEquals(loginPageTitle, expectedTitle);
			ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 1, 1, "Failed");
			Report.reportLog(LogStatus.FAIL, "User Unable to Login using Valid Credentials");
		}
	}
	
	@Test(testName="Login Using Multiple Users for login page",enabled=false,groups= {"Regression Test"},priority=2)
	public void test_multiple_users() {
		Report.startReport("C:\\ui_test\\com.orangehrm.demo\\Reports\\", "Test Login Functionality", "Login With Multiple Credentials");
		String loginPageTitle = loginPage.gettitle(BaseClass.getDriver());
		if(expectedTitle.equalsIgnoreCase(loginPageTitle)) {
			Assert.assertEquals(loginPageTitle, expectedTitle);				
			try {
				 	file = new File(filePath);
				 	rd = new FileReader(file);
				 	br = new BufferedReader(rd);
				 	int i = 0;
				 	while((line = br.readLine())!=null) {
				 		i = i+1;		
						if(i>1) {
							usrName = line.split(" ")[0];
							passWord = line.split(" ")[1];
							loginPage.loginToApp(usrName, passWord);
							String currURL = BaseClass.getDriver().getCurrentUrl();
							if(currURL.equalsIgnoreCase(expectedHomePageURL)) {
								Assert.assertEquals(expectedHomePageURL, currURL);
								ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 2, 1, "Passed");
								Report.reportLog(LogStatus.PASS, "User Logged in Successfully");
								homePage.logOutFromApp();
							}else if(currURL.equalsIgnoreCase(expectedURLonInvalidCred)){
								ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 2, 1, "Failed");
								Report.reportLog(LogStatus.FAIL, "User Unable to Login using Valid Credentials");
								
							}
						}
							
							
																					
				}
				 	br.close();
					rd.close();	
					
			}catch(FileNotFoundException f) {
				System.out.println(f.getMessage());
			}catch(IOException exp) {
				System.out.println(exp.getMessage());
			}
		}else {
			Assert.assertEquals(loginPageTitle, expectedTitle);
		}
	}

	@DataProvider(name="testData")
	public Object[][] testDataFeed(){
		ReadExcelFile config = new ReadExcelFile(excelPath);
		int rows = config.getRowCount(0);
		Object[][] credentials=new Object[rows][2];
		for(int i=1;i<rows;i++) {
			credentials[i][0]=config.getData(0, i, 0);
			credentials[i][1]=config.getData(0, i, 1);
		}
		return credentials;
	}
	
	@Test(testName="Login DDT using Data Providers",dataProvider="testData",enabled=false,groups= {"Regression Test"},priority=3)
	public void test_using_dataProvider(String usrName,String password) {
		Report.startReport("C:\\ui_test\\com.orangehrm.demo\\Reports\\", "Test Login Functionality", "Login With Testng Data Provider");
		String loginPageTitle = loginPage.gettitle(BaseClass.getDriver());
		if(expectedTitle.equalsIgnoreCase(loginPageTitle)) {
			Assert.assertEquals(loginPageTitle, expectedTitle);
			loginPage.loginToApp(usrName, password);
			String currURL = BaseClass.getDriver().getCurrentUrl();
			if(currURL.equalsIgnoreCase(expectedHomePageURL)) {
				Assert.assertEquals(expectedHomePageURL, currURL);
				ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 3, 1, "Passed");
				Report.reportLog(LogStatus.PASS, "User Logged in Successfully");
				homePage.logOutFromApp();
			}else if(currURL.equalsIgnoreCase(expectedURLonInvalidCred)){
				ExcelUtility.writeDataIntoCell(filePathExecutionStatus, "Sheet1", 3, 1, "Failed");
				Report.reportLog(LogStatus.FAIL, "User Unable to Login using Valid Credentials");
				
			}
		}	
		
	}
	

	@AfterMethod
    public void cleanUp(ITestResult result) {
		BaseClass.getDriver().quit();
		try {
			
		       if(ITestResult.FAILURE==result.getStatus()){
		             try {
		                    Report.reportLog(LogStatus.FAIL, result.getThrowable().toString());
		              } catch (Exception e) {
		                    e.printStackTrace();
		             }  
		       	} 
		     }
	     finally {
	  	    	   Reporter.log("Test Case Executed: "+result.getName(),true);
	  	    	   //Reporter.log("**************************************************************************************************************************************","info");
	               Report.endReport();
	  		}

    }
	
}
