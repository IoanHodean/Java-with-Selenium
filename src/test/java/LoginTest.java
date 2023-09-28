import org.example.*;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private final String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        System.out.println("Navigate to " + loginPageURL);
    }

    @Test(priority = 2)
    public void loginWithValidCredentialsTest() {

        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail.net");
        loginPage.insertPassword("idunno");
        loginPage.clickButton();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        String myAccount = myAccountPage.myAccountText();
        Assert.assertEquals(myAccount, "My Account");
    }

    @Test
    public void loginWithInvalidCredentialsTest() {

        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail.net");
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String errorMessage = loginPage.warning();
        Assert.assertEquals(errorMessage, "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithNoEmailTest() {
        driver.get(loginPageURL);
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String url = loginPage.warning();
        Assert.assertEquals(url, "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithNoPasswordTest() {
        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail..net");
        loginPage.clickButton();
        String errorMessage = loginPage.warning();
        Assert.assertEquals(errorMessage, "Warning: No match for E-Mail Address and/or Password.");
    }
     /*@Test
    public void loginMultipleTimesTest(){
        driver.get(loginPageURL);
        for (int i=0;i<5;i++) {
            loginPage.clear();
            loginPage.insertEmail("hodean.ioan@gmail.net");
            loginPage.insertPassword("iduno");
            loginPage.clickButton();
        }
        String warning= loginPage.warning();
        Assert.assertEquals(warning,"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
        }*/

    @Test
    public void forgottenPasswordInvalidEmail() {
        driver.get(loginPageURL);
        loginPage.clickForgottenPasswordHyperlink();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.insertRandomEmail();
        forgotPasswordPage.clickContinueButton();
        String expectedText = forgotPasswordPage.warning();
        Assert.assertEquals(expectedText, "Warning: The E-Mail Address was not found in our records, please try again!");

    }

    @Test
    public void forgottenPasswordValidEmail() {
        driver.get(loginPageURL);
        loginPage.clickForgottenPasswordHyperlink();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.insertEmail("hodean.ioan@gmail..net");
        forgotPasswordPage.clickContinueButton();
        String expected = loginPage.emailConfirmationLink();
        Assert.assertEquals(expected, "An email with a confirmation link has been sent your email address.");
    }

    @Test
    public void logInLogOutFromNavBar() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            driver.get(loginPageURL);
            loginPage.insertEmail("hodean.ioan@gmail.net");
            loginPage.insertPassword("idunno");
            loginPage.clickButton();
            Thread.sleep(2000);
            loginPage.clickLogOutFromNavBar();
        }
        logoutPage = new LogoutPage(driver);
        Assert.assertEquals(logoutPage.getLogoutMessage(), "You have been logged off your account. It is now safe to leave the computer.");
    }

    @Test
    public void logInLogOutFromColumnB() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            driver.get(loginPageURL);
            loginPage.insertEmail("hodean.ioan@gmail.net");
            loginPage.insertPassword("idunno");
            loginPage.clickButton();
            Thread.sleep(2000);
            loginPage.clickLogOutFromColumn();
        }
        logoutPage = new LogoutPage(driver);
        Assert.assertEquals(logoutPage.getLogoutMessage(), "You have been logged off your account. It is now safe to leave the computer.");
    }

    @AfterTest
    public void close() {
        driver.quit();
    }


}
