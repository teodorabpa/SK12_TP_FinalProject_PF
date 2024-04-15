package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    private WebDriverWait wait;
    @FindBy(xpath = "//i[contains(@class, 'fa-user-edit')]")
    private WebElement editProfileButton;

    @FindBy(xpath = "//p")
    private WebElement publicInfoText;

    public ProfilePage(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public boolean isUrlLoaded(String userId){
        return wait.until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL+userId));
    }

    public void clickEditProfileButton(){
        wait= (WebDriverWait) wait.withTimeout(Duration.ofSeconds(300));
        wait.until(ExpectedConditions.elementToBeClickable(editProfileButton));
        editProfileButton.click();
    }

    public String getPublicInfoText() {
        wait.until(ExpectedConditions.elementToBeClickable(publicInfoText));
        return publicInfoText.getText();

    }


}
