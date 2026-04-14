package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Log;

import java.time.Duration;

import static org.testng.Assert.*;

public class Login_Failed extends BaseTest {
    @Test
    public void testValidLogin(){
        Log.info("Starting Login Test...");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin@yourstore.co");
        loginPage.enterPassword("admi");
        loginPage.enterLogin();
        Log.error("Checking the error message content...");
        System.out.println("The error message is: " +loginPage.ErrorcredentialsPop());
        assertEquals(loginPage.ErrorcredentialsPop(), "Epic sadface: Username and password do not match any user in this service");
    }
}
