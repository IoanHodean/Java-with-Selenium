import org.example.ForgotPasswordPage;
import org.example.LoginPage;
import org.example.MyAccountPage;
import org.example.RegisterAccountPage;
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
    private String loginPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + loginPageURL);
        driver.get(loginPageURL);
        loginPage= new LoginPage(driver);
    }
    @Test
    public void loginWithValidCredentialsTest(){

        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.insertPassword("idunno");
        loginPage.clickButton();
        MyAccountPage myAccountPage=new MyAccountPage(driver);
        String myAccount=myAccountPage.MyAccountText();
        Assert.assertEquals(myAccount,"My Account");
    }
    @Test
    public void loginWithInvalidCredentialsTest(){

        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String errorMessage=loginPage.warning();
        Assert.assertEquals(errorMessage,"Warning: No match for E-Mail Address and/or Password.");
    }
    @Test
    public void loginWithNoEmailTest(){
        driver.get(loginPageURL);
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String url=loginPage.warning();
        Assert.assertEquals(url," Warning: No match for E-Mail Address and/or Password.");
    }
    @Test
    public void loginWithNoPasswordTest(){
        driver.get(loginPageURL);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.clickButton();
        String errorMessage=loginPage.warning();
        Assert.assertEquals(errorMessage,"Warning: No match for E-Mail Address and/or Password.");
    }
    @Test
    public void loginMultipleTimesTest(){
        driver.get(loginPageURL);
        for (int i=0;i<5;i++) {
            loginPage.clear();
            loginPage.insertEmail("hodean.ioan@gmail.com");
            loginPage.insertPassword("iduno");
            loginPage.clickButton();
        }
        String warning= loginPage.warning();
        Assert.assertEquals(warning,"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
        }

    @Test
    public void forgottenPasswordInvalidEmail(){
        driver.get(loginPageURL);
        loginPage.clickForgottenPasswordHyperlink();
        ForgotPasswordPage forgotPasswordPage=new ForgotPasswordPage(driver);
        forgotPasswordPage.insertRandomEmail();
        forgotPasswordPage.clickContinueButton();
        String expectedText= forgotPasswordPage.warning();
        Assert.assertEquals(expectedText, "Warning: The E-Mail Address was not found in our records, please try again!");

    }
    @Test
    public void forgottenPasswordValidEmail(){
        driver.get(loginPageURL);
        loginPage.clickForgottenPasswordHyperlink();
        ForgotPasswordPage forgotPasswordPage=new ForgotPasswordPage(driver);
        forgotPasswordPage.insertEmail("hodean.ioan@gmail.com");
        forgotPasswordPage.clickContinueButton();
        String expected=loginPage.emailConfirmationLink();
        Assert.assertEquals(expected,"An email with a confirmation link has been sent your email address.");
    }

@AfterTest
    public void close(){
        driver.quit();
}


}
