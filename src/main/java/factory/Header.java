package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@id='nav-link-login']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@id='nav-link-profile']")
    private WebElement profilePageLink;
    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement signOutLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;

    public Header(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    public void clickProfile() {
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(35));
        wait = (WebDriverWait) wait.withTimeout(Duration.ofSeconds(35));
        wait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

    public void clickSignOut() {
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
        signOutLink.click();
    }

    public void clickNewPost() {
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(newPostLink));
        newPostLink.click();
    }

}