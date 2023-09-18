import org.example.AccountCreatedPage;
import org.example.RegisterAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static util.TestUtil.generateRandomEmail;

public class RegisterAccountTest {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
        private String registerPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + registerPageURL);
        driver.get(registerPageURL);
        registerAccountPage= new RegisterAccountPage(driver);
    }

    @Test
    public void registerNewAccountMandatoryFieldsTest() throws Exception {
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

    }


    @Test
    public void registerWithoutPrivacyPolicyTest(){
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.clickContinue();

        String actualValue= registerAccountPage.getErrorMessage();
        String expectedValue="Warning: You must agree to the Privacy Policy! ";
        Assert.assertEquals(actualValue, expectedValue, "Error message is not the expected one");
    }
    @Test
    public void registerAccountWithoutFirstNameTest(){
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
    public void registerAccountWithoutLastNameTest(){
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
    public void registerAccountWithoutEmailTest(){
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
    public void registerAccountWithoutPhoneNumberTest(){
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
    public void registerAccountWithInvalidPhoneNumberTest(){
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("sads!#%$s");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getTelephoneErrorMessage();
        String expectedText = "The format input is not correct.";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }
    @Test
    public void registerAccountWithoutPasswordTest(){
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
    public void registerAccountWithoutConfirmPasswordTest(){
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
    public void registerAccountWithDifferentConfirmationPasswordInputTest(){
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
    public void registerAccountWithAllBlankFieldsAndNoPrivacyAcceptedTest(){
        registerAccountPage.clickContinue();
        // aT=actual text, eT=expected test
        String aTFirstName=registerAccountPage.getFirstNameErrorMessage();
        String eTFirstName="First Name must be between 1 and 32 characters!";
        Assert.assertEquals(aTFirstName, eTFirstName, "Error message is not the expected one");

        String aTLastName=registerAccountPage.getLastNameErrorMessage();
        String eTLastName="Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(aTLastName, eTLastName, "Actual text is not the expected one.");
        String aTEmail= registerAccountPage.getEmailErrorMessage();
        String eTEmail="E-Mail Address does not appear to be valid!";
        Assert.assertEquals(aTEmail, eTEmail, "Actual text is not the expected one.");
        String aTTelephone= registerAccountPage.getTelephoneErrorMessage();
        String eTTelephone="Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(aTTelephone, eTTelephone, "Actual text is not the expected one.");
        String aTPassword= registerAccountPage.getPasswordErrorMessage();
        String eTPassword="Password must be between 4 and 20 characters!";
        Assert.assertEquals(aTPassword, eTPassword, "Actual text is not the expected one.");
  //      String aTConfirmPassword= registerAccountPage.getConfirmationPasswordErrorMessage();
  //      String eTConfirmPassword="Password confirmation does not match password!";
  //      Assert.assertEquals(aTConfirmPassword, eTConfirmPassword, "Actual text is not the expected one.");
        String aTPolicy=registerAccountPage.getErrorMessage();;
        String eTPolicy= "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(aTPolicy, eTPolicy, "Error message is not the expected one");
    }
@Test
    public void RegisterAccountWithSubscribe() throws InterruptedException {
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
}
@Test
    public void RegisterAccountWithoutSubscribe() throws InterruptedException {
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
}
    @AfterTest
    public void tearDown() {driver.quit();}
}
