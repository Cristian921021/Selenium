package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By usernameTextBox = By.id("user-name");
    private By passwordTextBox = By.id("password");
    private By loginButton = By.xpath("//*[@id=\"login-button\"]");
    private By errorCredentials = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String usernamenop){
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(usernamenop);
    }
    public void enterPassword(String passwordnop){
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(passwordnop);
    }
    public void enterLogin(){
        Log.info("Clicking the login button...");
        driver.findElement(loginButton).click();
    }
    public String ErrorcredentialsPop (){
        WebElement error_mess = driver.findElement(errorCredentials);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(d -> error_mess.isDisplayed());
        String MessageCredentials = error_mess.getText();
        return MessageCredentials;
    }
}
