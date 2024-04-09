package factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@formcontrolname = 'username']")
    private WebElement usernameTextField;

    @FindBy(xpath = "//input[@formcontrolname = 'email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//input[@formcontrolname = 'birthDate']")
    private WebElement dateOfBirthField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordTextField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordTextField;

    @FindBy(xpath = "//textarea[@name='pulic-info']")
    private WebElement publicInfoTextField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    public RegisterPage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        return wait.until(ExpectedConditions.urlToBe(REGISTER_PAGE_URL));
    }

    public void fillInUserName(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        usernameTextField.sendKeys(username);
    }

    public void fillInEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
    }

    public void fillInDateOfBirth(String birthDay, String birthMonth, String birthYear) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthField));
        dateOfBirthField.sendKeys(birthDay);
        dateOfBirthField.sendKeys(birthMonth);
        dateOfBirthField.sendKeys(Keys.TAB);
        dateOfBirthField.sendKeys(birthYear);
    }

    public void fillInPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
    }

    public void fillInConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordTextField));
        confirmPasswordTextField.sendKeys(confirmPassword);
    }

    public void fillInPublicInfo(String publicInfo) {
        wait.until(ExpectedConditions.visibilityOf(publicInfoTextField));
        publicInfoTextField.sendKeys(publicInfo);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

}

