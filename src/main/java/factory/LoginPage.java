package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String LOGIN_PAGE_URL = "http://training.skillo-bg.com:4200/users/login";
    private final WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(id="defaultLoginFormUsername")
    private WebElement usernameTextField;

    @FindBy(id="defaultLoginFormPassword")
    private WebElement passwordTextField;

    @FindBy(xpath = "//*[@class='remember-me']/input[@type='checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(id="sign-in-button")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@href='/users/register']")
    private WebElement registerButton;

    public LoginPage(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public boolean isUrlLoaded(){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
    }
    public void fillInUserName(String username){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        usernameTextField.sendKeys(username);
    }
    public void fillInPassword(String password){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
    }
    public void checkRememberMe(){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        rememberMeCheckbox.click();
    }
    public boolean isCheckedRememberMe(){
        return rememberMeCheckbox.isSelected();
    }

    public void clickSignIn(){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
    public void clickRegisterButton(){
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }
    public void navigateTo() {
        this.webDriver.get(LOGIN_PAGE_URL);
    }
}
