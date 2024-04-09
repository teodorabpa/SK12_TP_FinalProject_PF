package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver webDriver;

    @FindBy(xpath = "//i[@class='fas fa-user-edit ng-star-inserted']")
    private WebElement editProfileButton;

    public ProfilePage(WebDriver driver){
        this.webDriver = driver;
    }
    public boolean isUrlLoaded(String userId){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL+userId));
    }

    public void clickEditProfileButton(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(editProfileButton));
        editProfileButton.click();
    }

}
