package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Log;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
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
    public void tearDown(){
        if (driver != null) {
            log.info("Closing the browser...");
            driver.quit();
        }
    }
}
