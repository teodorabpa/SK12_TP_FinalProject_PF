package AutomatedWebTests;

import factory.Header;
import factory.HomePage;
import factory.LoginPage;
import factory.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AllTests {
    ChromeDriver webDriver;
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        webDriver.close();
    }

    @DataProvider(name="getUserCredentials")
    public Object[][] getUserCredentials() {
        return new Object[][]{
                {"T@P.com3", "T@P.com3", "5626"},
        };
    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void loginTest(String username, String password, String userId){
        HomePage homePage = new HomePage (webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage (webDriver);
        ProfilePage profilePage = new ProfilePage (webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUserLoaded(), "Homepage url is not loaded");
        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me is not checked");
        loginPage.clickSignIn();
        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for " + userId + " user");
        header.clickSignOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        }
    }