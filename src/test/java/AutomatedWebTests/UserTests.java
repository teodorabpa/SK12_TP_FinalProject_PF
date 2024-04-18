package AutomatedWebTests;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class UserTests extends TestObject {

    private String credential;

    @DataProvider(name="getUserCredentials")
    public Object[][] getUserCredentials() {
        return new Object[][]{
                {"T@P.com3", "T@P.com3", "5626"},
        };
    }

    @Test
    public void homePageTest() {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Homepage url is not loaded");
        Assert.assertTrue(header.isHomeLinkDisplayed(), "'Home' link is not visible in header");
        Assert.assertTrue(header.isLoginLinkDisplayed(), "'Login' link is not visible in header");
    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void successfulLoginTest(String username, String password, String userId) {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);
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
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Successful login!");
        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for " + userId + " user");
        header.clickSignOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void loginUnsuccessfulWrongPasswordTest(String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage (webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage (webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password +"1");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Ivalid password");

    }

    @Test
    public void loginUnsuccessfulUsernameIsMissingTest() {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage (webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage (webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName("");
        loginPage.fillInPassword("password");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "UsernameOrEmail cannot be empty");

    }

    @Test
    public void loginUnsuccessfulPasswordMissingTest(){
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage (webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage (webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName("T@P.com3");
        loginPage.fillInPassword("");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Password cannot be empty");

    }

    @Test(dataProvider = "getUserCredentials", groups = "Login")
    public void logOutTest(String username, String password, String userId) {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header (webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickSignIn();
        header.clickSignOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page url is not loaded");
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Successful logout!");

    }

    @DataProvider(name="getUserRegisterCredentials")
    public Object[][] getUserRegisterCredentials() {
        credential = generateUniqueCredential();
        return new Object[][]{
                {credential, credential, credential, credential},
        };
    }

    @Test(dataProvider = "getUserRegisterCredentials", groups = "Login")
    public void registerTest(String username, String email, String password, String confirmPassword) {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

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
        String message = toastContainer.getAlertText();
        Assert.assertEquals(message, "Successful register!");

    }
    @Test(groups = "Login", dependsOnMethods = "registerTest")
        public void registerTestFail() {
        WebDriver webDriver = super.getWebDriver();
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.clickRegisterButton();
        registerPage.fillInUserName(credential);
        registerPage.fillInEmail(credential);
        registerPage.fillInPassword(credential);
        registerPage.fillInConfirmPassword(credential);
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
        WebDriver webDriver = super.getWebDriver();
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
    private String generateUniqueCredential() {
        String baseString = "T@P.com";
        Random random = new Random();
        return baseString + random.nextInt(10000);
    }
}