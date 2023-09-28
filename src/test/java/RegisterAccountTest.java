import org.example.AccountCreatedPage;
import org.example.LoginPage;
import org.example.RegisterAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static util.TestUtil.generateRandomEmail;

public class RegisterAccountTest {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private LoginPage loginPage;
    private final String registerPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        System.out.println("Navigate to " + registerPageURL);
        registerAccountPage = new RegisterAccountPage(driver);
        loginPage = new LoginPage(driver);
    }


    @Test
    public void registerNewAccountMandatoryFieldsTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        //  Thread.sleep(5000);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
        loginPage.clickLogOutFromNavBar();
    }


    @Test
    public void registerWithoutPrivacyPolicyTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.clickContinue();
        String actualValue = registerAccountPage.getErrorMessage();
        String expectedValue = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(actualValue, expectedValue, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutFirstNameTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getFirstNameErrorMessage();
        String expectedText = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithoutLastNameTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getLastNameErrorMessage();
        String expectedText = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithoutEmailTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getEmailErrorMessage();
        String expectedText = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithoutPhoneNumberTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getTelephoneErrorMessage();
        String expectedText = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithoutPasswordTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getPasswordErrorMessage();
        String expectedText = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithoutConfirmPasswordTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getConfirmationPasswordErrorMessage();
        String expectedText = "Password confirmation does not match password!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithDifferentConfirmationPasswordInputTest() {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getConfirmationPasswordErrorMessage();
        String expectedText = "Password confirmation does not match password!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerAccountWithAllBlankFieldsAndNoPrivacyAcceptedTest() {
        driver.get(registerPageURL);
        registerAccountPage.clickContinue();
        // aT=actual text, eT=expected test
        String aTFirstName = registerAccountPage.getFirstNameErrorMessage();
        String eTFirstName = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(aTFirstName, eTFirstName, "Error message is not the expected one");

        String aTLastName = registerAccountPage.getLastNameErrorMessage();
        String eTLastName = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(aTLastName, eTLastName, "Actual text is not the expected one.");
        String aTEmail = registerAccountPage.getEmailErrorMessage();
        String eTEmail = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(aTEmail, eTEmail, "Actual text is not the expected one.");
        String aTTelephone = registerAccountPage.getTelephoneErrorMessage();
        String eTTelephone = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(aTTelephone, eTTelephone, "Actual text is not the expected one.");
        String aTPassword = registerAccountPage.getPasswordErrorMessage();
        String eTPassword = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(aTPassword, eTPassword, "Actual text is not the expected one.");
        //      String aTConfirmPassword= registerAccountPage.getConfirmationPasswordErrorMessage();
        //      String eTConfirmPassword="Password confirmation does not match password!";
        //      Assert.assertEquals(aTConfirmPassword, eTConfirmPassword, "Actual text is not the expected one.");
        String aTPolicy = registerAccountPage.getErrorMessage();

        String eTPolicy = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(aTPolicy, eTPolicy, "Error message is not the expected one");
    }

    @Test
    public void RegisterAccountWithSubscribe() throws InterruptedException {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.subscribeYes();
        registerAccountPage.clickContinue();
        Thread.sleep(2000);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void RegisterAccountWithoutSubscribe() throws InterruptedException {
        driver.get(registerPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.subscribeNo();
        registerAccountPage.clickContinue();
        Thread.sleep(2000);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
        loginPage.clickLogOutFromNavBar();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}