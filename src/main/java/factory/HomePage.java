package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver webDriver;

    public HomePage(WebDriver driver) {this.webDriver = driver; }
    public void navigateTo() {this.webDriver.get(HOME_URL);}
    public boolean isUserLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
}
