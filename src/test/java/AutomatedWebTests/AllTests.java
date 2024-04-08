package AutomatedWebTests;

import factory.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        Assert.assertTrue(homePage.isUserLoaded(), "Homepage url is not loaded");
    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void loginTest(String username, String password, String userId) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

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
        Assert.assertTrue(homePage.isUserLoaded(), "Homepage url is not loaded");

    }

    @DataProvider(name="getUserRegisterCredentials")
    public Object[][] getUserRegisterCredentials() {
        return new Object[][]{
                {"Tedi@P.com3", "Tedi@P.com3", "17", "09", "1984", "Ted@P.com3", "Ted@P.com3", "Loren ipsum sit amet dolor"},
        };
    }

    @Test(dataProvider = "getUserRegisterCredentials", groups = "Login")
    public void registerTest(String username, String email, String birthDay, String birthMonth, String birthYear, String password, String confirmPassword, String publicInfo) {
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
        registerPage.fillInDateOfBirth(birthDay);
        registerPage.fillInDateOfBirth(birthMonth);
        registerPage.fillInDateOfBirth(birthYear);
        registerPage.fillInPassword(password);
        registerPage.fillInConfirmPassword(confirmPassword);
        registerPage.fillInPublicInfo(publicInfo);
        registerPage.clickSignIn();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login page");

    }

}