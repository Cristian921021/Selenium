package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(xpath = "//*[@id=\"login-button\"]" )
    WebElement loginButton;

    @FindBy (xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")
    WebElement errorCredentials;

    @FindBy (xpath = "//*[@id=\"header_container\"]/div[1]/div[2]/div")
    WebElement SwagProducts_tittle;

//    private By usernameTextBox = By.id("user-name");
//    private By passwordTextBox = By.id("password");
//    private By loginButton = By.xpath("//*[@id=\"login-button\"]");
//    private By errorCredentials = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String usernamenop){
        usernameTextBox.clear();
        usernameTextBox.sendKeys(usernamenop);
//        driver.findElement(usernameTextBox).clear();
//        driver.findElement(usernameTextBox).sendKeys(usernamenop);
    }
    public void enterPassword(String passwordnop){

        passwordTextBox.clear();
        passwordTextBox.sendKeys(passwordnop);
//        driver.findElement(passwordTextBox).clear();
//        driver.findElement(passwordTextBox).sendKeys(passwordnop);
    }
    public void enterLogin(){
        Log.info("Clicking the login button...");
        loginButton.click();
//        driver.findElement(loginButton).click();
    }
    public String ErrorcredentialsPop (){

        WebElement error_mess = errorCredentials;
//        WebElement error_mess = driver.findElement(errorCredentials);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(d -> error_mess.isDisplayed());
        String MessageCredentials = error_mess.getText();
        return MessageCredentials;
    }

    public String NextPageTittle (){

        WebElement nextpagetitle = SwagProducts_tittle;
//        WebElement error_mess = driver.findElement(SwagProducts_tittle);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(d -> nextpagetitle.isDisplayed());
        String Tittletwo = nextpagetitle.getText();
        return Tittletwo;
    }

}
