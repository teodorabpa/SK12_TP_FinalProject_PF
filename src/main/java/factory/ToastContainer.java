package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToastContainer {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    @FindBy (xpath = "//div[@id='toast-container']")
    private WebElement toastContainer;

    public ToastContainer(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(this.webDriver, this);
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.visibilityOf(toastContainer));
        WebElement message = toastContainer.findElement(By.xpath("//div[@role='alertdialog']"));
        return message.getText();
    }

}
