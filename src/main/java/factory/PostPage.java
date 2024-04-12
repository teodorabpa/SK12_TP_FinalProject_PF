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
    private WebDriverWait wait;

    @FindBy(xpath = "//h3[text()='Post a picture to share with your awesome followers']")
    private WebElement newPostTitle;
    @FindBy(xpath = "//input[@class='form-control input-lg'][@type='text']")
    private WebElement uploadPictureText;
    @FindBy(name="caption")
    private WebElement postCaption;
    @FindBy(id="create-post")
    private WebElement createPostButton;

    public PostPage(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }
    public boolean isNewPostLoaded() {
        //WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(newPostTitle));
        return newPostTitle.isDisplayed();
    }

    public void uploadPicture(File file){
        WebElement uploadFile = webDriver.findElement(By.xpath("//*[@class='form-group']/input[@type='file']"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }
    public boolean isImageUploaded(String fileName) {
        String actualText=uploadPictureText.getAttribute("placeholder");
        if(actualText.equals(fileName)) {
            return true;
        }
            return false;
    }
    public String uploadedImageText() {
        return uploadPictureText.getAttribute("placeholder");
    }

    public void typePostCaption (String text){
       postCaption.sendKeys(text);
    }

    public void clickCreatePost() {
        createPostButton.isEnabled();
        createPostButton.click();
    }
}
