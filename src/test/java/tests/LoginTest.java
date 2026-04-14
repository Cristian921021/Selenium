package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Log;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin(){
        Log.info("Starting Login Test...");
        System.out.println("The tittle of the page is " +driver.getTitle());
        LoginPage loginPage = new LoginPage(driver);
        Log.info("Adding the credentials...");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.enterLogin();
        Log.info("Test Finished");
    }

}
