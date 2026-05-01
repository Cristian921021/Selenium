package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

   @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }
    @AfterSuite
    public void teardownReport() {

       extent.flush();
       String reportPath = ExtentReportManager.reportPath;
        EmailUtils.sendTestReport(reportPath);
    }

    @BeforeMethod
    public void setUp(){
        Log.info("Starting WebDriver without leaks nuisances...");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Log.info("Navigating to URL...");
        driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void tearDown(ITestResult result){
       if(result.getStatus() == ITestResult.FAILURE){
           String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginIssue");
           test.fail("Test Failed... please check the screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
       } else if (result.getStatus() == ITestResult.SUCCESS) {
           String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginSuccesful");
           test.pass("Test Successful... please check the screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
       }
        if (driver != null) {
            log.info("Closing the browser...");
            driver.quit();
        }
    }
}
