package Dowload_Upload_Testobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;

public class DownloadTest extends TestObject{
    @Test
    public void testDownload() throws InterruptedException {
        WebDriver driver = super.getWebDriver();
        driver.get("https://demoqa.com/upload-download");
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.isDisplayed();
        downloadButton.click();


        String fileName = "sampleFile.jpeg";
        File file = new File(DOWNLOAD_DIR.concat(fileName));


    }
}
