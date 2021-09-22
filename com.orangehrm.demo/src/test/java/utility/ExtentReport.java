package utility;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReport implements ITestListener {
	
	protected static ExtentReports reports;
	protected static ExtentTest logger;
	private static String resultpath = getResultPath();


	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private static String getResultPath() {

		resultpath = "result";//new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	String ReportLocation = "C:\\ui_test\\com.orangehrm.demo\\Reports\\";

	public void onTestStart(ITestResult result) {

		logger = reports.startTest(result.getMethod().getMethodName());
		logger.log(LogStatus.INFO, result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, "Test is Pass");

	}

	public void onTestFailure(ITestResult result) {
		logger.log(LogStatus.FAIL, "Test is Fail");

	}

	public void onTestSkipped(ITestResult result) {
		logger.log(LogStatus.SKIP, "Test is skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		reports = new ExtentReports(ReportLocation + "ExtentReport.html");
		logger = reports.startTest("*****Test Execution Report*****");

	}

	public void onFinish(ITestContext context) {
		reports.endTest(logger);
		reports.flush();

	}


}
