package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

import static org.testng.Assert.assertEquals;

public class Login_Missing_credentials extends BaseTest {
    @Test
    public void testValidLogin(){
        Log.info("Starting Login Test...");
        test = ExtentReportManager.createTest("Missing Credentials");
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);
        test.info("Navigated to Login Page");
        Log.info("Adding the credentials...");
        test.info("Adding the credentials...");
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        test.info("Clicking on the Login Button");
        loginPage.enterLogin();
        Log.error("Checking the error message content...");
        test.info("Checking error message");
        System.out.println("The error message is: " +loginPage.ErrorcredentialsPop());
        assertEquals(loginPage.ErrorcredentialsPop(), "Epic sadface: Username is required");
        test.pass("Missing username message obtained");
        Log.info("Test Finished");
    }
}
