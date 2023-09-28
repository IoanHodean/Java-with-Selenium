import org.example.AccountCreatedPage;
import org.example.ChangePasswordPage;
import org.example.RegisterAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static util.TestUtil.generateRandomEmail;

public class ChangePasswordTest {

    private WebDriver driver;
    private ChangePasswordPage changePasswordPage;
    private RegisterAccountPage registerAccountPage;
    private final String changePasswordURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/password";


    @BeforeClass
    public void setsUp() {
        driver = new ChromeDriver();
        changePasswordPage = new ChangePasswordPage(driver);
        registerAccountPage = new RegisterAccountPage(driver);
        System.out.println("Initialize driver.");
        createAccount();


    }

    @Test
    public void ChangePasswordWithValidCredentials() {
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idunno");
        changePasswordPage.passwordConfirmInput("idunno");
        changePasswordPage.clickContinueButton();
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String expected = accountCreatedPage.getSuccessText();
        Assert.assertEquals(expected, "Success: Your password has been successfully updated.");
    }

    @Test
    public void ChangePasswordWithMismatchingInput() {
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idunno");
        changePasswordPage.passwordConfirmInput("idunnos");
        changePasswordPage.clickContinueButton();
        String expected = changePasswordPage.getMismatchPasswordErrorText();
        Assert.assertEquals(expected, "Password confirmation does not match password!");
    }

    @Test
    public void ChangePasswordWithInvalidInsufficientInput() {
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idu");
        changePasswordPage.passwordConfirmInput("idu");
        changePasswordPage.clickContinueButton();
        String expected = changePasswordPage.getInvalidInputPasswordErrorText();
        Assert.assertEquals(expected, "Password must be between 4 and 20 characters!");
    }

    @Test
    public void ChangePasswordWithInvalidExceedingInputTest() throws InterruptedException {
        // Test fails because it has a bug
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idubfieqfgbeqgbeqgbqegneqfgqesdgfeaqdge");
        changePasswordPage.passwordConfirmInput("idubfieqfgbeqgbeqgbqegneqfgqesdgfeaqdge");
        changePasswordPage.clickContinueButton();
        Thread.sleep(1000);
        String expected = changePasswordPage.getInvalidInputPasswordErrorText();
        Assert.assertEquals(expected, "Password must be between 4 and 20 characters!");

    }

    @AfterTest
    public void close() {
        driver.quit();
    }

    public void createAccount() {
        System.out.println("Creating new account to be use in test...");
        String registerURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
        driver.get(registerURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("idunno");
        registerAccountPage.setPasswordConfirmInput("idunno");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        System.out.println("Creating new account to be use in tests...Done");
    }
}
