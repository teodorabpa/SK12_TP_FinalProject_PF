package AutomatedWebTests;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostTests extends TestObject {
    @DataProvider(name="getUserCredentials")
    public Object[][] getUserCredentials() {
            File postPicture = new File("src\\test\\resources\\upload\\testUpload.jpg");
            String caption = "Testing upload file";
        return new Object[][]{
                {"T@P.com3", "T@P.com3", "5626", postPicture, caption},
        };
    }
    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void testCreatePost(String username, String password, String userId, File postPicture, String caption){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");
        loginPage.clickSignIn();

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostFormLoaded(), "The new post form is not loaded");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("testUpload.jpg"), "Image is not uploaded!");
        Assert.assertEquals(actualImageText, "testUpload.jpg", "Incorrect image is uploaded!");

        postPage.typePostCaption(caption);
        postPage.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");
    }
}
