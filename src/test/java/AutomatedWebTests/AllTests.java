package AutomatedWebTests;

import factory.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

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

    @Test(groups = "Login")
    public void homePageTest() {
        HomePage homePage = new HomePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Homepage url is not loaded");
    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void loginTest(String username, String password, String userId) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Homepage url is not loaded");
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

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void loginUnsuccessfulTest(String username, String password, String userId){
        HomePage homePage = new HomePage (webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage (webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password +"1");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");

    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void logOutTest(String username, String password, String userId) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickSignIn();
        header.clickSignOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");

    }

    @DataProvider(name="getUserRegisterCredentials")
    public Object[][] getUserRegisterCredentials() {
        return new Object[][]{
                {"Ted333@P.com", "Ted333@P.com", "Ted333@P.com", "Ted333@P.com"},
        };
    }

    @Test(dataProvider = "getUserRegisterCredentials", groups = "Login")
    public void registerTest(String username, String email, String password, String confirmPassword) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isUrlLoaded(), "Current page is not Register page");
        registerPage.fillInUserName(username);
        registerPage.fillInEmail(email);
        registerPage.fillInPassword(password);
        registerPage.fillInConfirmPassword(confirmPassword);
        registerPage.clickSignIn();
        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not Home page");

    }
    @Test(dataProvider = "getUserRegisterCredentials", groups = "Login", dependsOnMethods = "registerTest")
        public void registerTestFail(String username, String email, String password, String confirmPassword) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.clickRegisterButton();
        registerPage.fillInUserName(username);
        registerPage.fillInEmail(email);
        registerPage.fillInPassword(password);
        registerPage.fillInConfirmPassword(confirmPassword);
        registerPage.clickSignIn();
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Username taken");

    }

    @DataProvider(name="getEditUserCredentials")
    public Object[][] getEditUserCredentials() {
        return new Object[][]{
                {"Ted3@P.com", "Ted3@P.com", "public info text"},
        };
    }
    @Test(dataProvider = "getEditUserCredentials", groups = "Login")
    public void editProfile(String username, String password, String publicInfo) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        ModifyProfilePage modifyProfilePage = new ModifyProfilePage(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickSignIn();
        header.clickProfile();
        profilePage.clickEditProfileButton();
        modifyProfilePage.fillInPublicInfoTextField(publicInfo);
        modifyProfilePage.clickSave();
        String detailedPublicInfo = profilePage.getPublicInfoText();
        Assert.assertTrue(detailedPublicInfo.contains(publicInfo), "Public info is not correct.");
        System.out.println(detailedPublicInfo);
        System.out.println(publicInfo);
        }
}