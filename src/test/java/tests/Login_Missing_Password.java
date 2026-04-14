package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Log;

import static org.testng.Assert.assertEquals;

public class Login_Missing_Password extends BaseTest {
    @Test
    public void testValidLogin(){
        Log.info("Starting Login Test...");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin@yourstore.co");
        loginPage.enterPassword("");
        loginPage.enterLogin();
        Log.error("Checking the error message content...");
        System.out.println("The error message is: " +loginPage.ErrorcredentialsPop());
        assertEquals(loginPage.ErrorcredentialsPop(), "Epic sadface: Password is required");
    }
}
