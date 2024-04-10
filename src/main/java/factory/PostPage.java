package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class PostPage {

    private final WebDriver webDriver;
    @FindBy(xpath = "//h3[text()='Post a picture to share with your awesome followers']")
    private WebElement newPostTitle;
    public PostPage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public boolean isNewPostLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(newPostTitle));
        return newPostTitle.isDisplayed();
    }

    public void uploadPicture(File file){
        WebElement uploadFile = webDriver.findElement(By.xpath("//*[@class='form-group']/input[@type='file']"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }

}
