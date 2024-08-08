package api_utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;   //UI of the Report
	public ExtentReports extent;   //Populate common info(OS, Module, User/name, browser)
	public ExtentTest test;   //creating test case entries, updating test status
	
	public void onStart(ITestContext testContext) {
		
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		//repName = "Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReports.html"); //specific location of the report
		
		sparkReporter.config().setDocumentTitle("Automation Report");  //Title of Report
		sparkReporter.config().setReportName("GoRest Framework"); //Name of the Report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Teser Name", "Prabhu");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser Type", "Chrome");
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		//test.assignCategory(result.getMethod().getGroups());
		//test.createNode(result.getName());
		test.log(Status.PASS, "Test "+ result.getName()+" Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		//test.createNode(result.getName());
		//test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test "+ result.getName()+" Failed");
		test.log(Status.FAIL, "Reason: "+ result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		//test.createNode(result.getName());
		//test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test "+ result.getName()+" Skipped");
		//test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
