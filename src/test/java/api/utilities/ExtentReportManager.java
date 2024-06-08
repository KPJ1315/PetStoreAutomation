package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	public String reportName;

	@Override
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		reportName = "TestReport"+timeStamp+".html"; // report file name

		sparkReporter = new ExtentSparkReporter(".\\Reports\\"+reportName); // specify location of report



		sparkReporter.config().setDocumentTitle("Automation Report for Rest API");
		sparkReporter.config().setReportName("Pet Store Rest API Framework");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Application", "Pet Store Usrs Rest API's");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "Test User");
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.FAIL, "Test failed");
		extentTest.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.SKIP, "Test Skipped");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
}
