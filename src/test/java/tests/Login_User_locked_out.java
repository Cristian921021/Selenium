package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Log;

import static org.testng.Assert.assertEquals;

public class Login_User_locked_out extends BaseTest {
    @Test
    public void testValidLogin(){
        Log.info("Starting Login Test...");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.enterLogin();
        Log.error("Checking the error message content...");
        System.out.println("The error message is: " +loginPage.ErrorcredentialsPop());
        assertEquals(loginPage.ErrorcredentialsPop(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
