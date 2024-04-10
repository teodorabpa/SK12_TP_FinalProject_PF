package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModifyProfilePage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//textarea[@formcontrolname='publicInfo']")
    private WebElement publicInfoTextField;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement saveButton;

    public ModifyProfilePage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(this.webDriver, this);
    }

    public void fillInPublicInfoTextField (String publicInfo) {
        wait.until(ExpectedConditions.visibilityOf(publicInfoTextField));
        publicInfoTextField.clear();
        publicInfoTextField.sendKeys(publicInfo);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }
}
