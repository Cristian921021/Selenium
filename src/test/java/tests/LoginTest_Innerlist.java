package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class LoginTest_Innerlist extends BaseTest {

    @DataProvider(name = "Login Users Data")
    public Object [][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Hoja1");
        int rowCount = ExcelUtils.getRowCount();
        Object [][] data = new Object[rowCount-1][2];

        for (int i = 1; i < rowCount; i++){
                data[i-1][0] = ExcelUtils.getCellData(i, 0); //username
                data[i-1][1] = ExcelUtils.getCellData(i,1); //password
        }
        ExcelUtils.closeExcel();
        return data;
    }
    @DataProvider(name = "Login Users Data 2")
    public Object [][] getData(){
        return new Object[][] {
                {"standard_user","secret_sauce"},
                {"problem_user","secret_sauce"},
                {"performance_glitch_user","secret_sauce"},
                {"error_user","secret_sauce"},
                {"visual_user","secret_sauce"}
        };
    }

    @Test (dataProvider = "Login Users Data 2")
    public void testValidLogin(String username, String password){
        Log.info("Starting Login Test...");
        test = ExtentReportManager.createTest("Login Test "+username);
        System.out.println("The tittle of the page is " +driver.getTitle());
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);
        test.info("Navigated to Login Page");
        Log.info("Adding the credentials...");
        test.info("Adding the credentials...");
//        loginPage.enterUsername("standard_user");
//        loginPage.enterPassword("secret_sauce");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.info("Clicking on the Login Button");
        loginPage.enterLogin();
        System.out.println("The tittle of the next page is: " +loginPage.NextPageTittle());
        assertEquals(loginPage.NextPageTittle(), "Swag Labs");
        test.pass("New Page reached");
        Log.info("Test Finished");
    }

}
