import org.example.MyAccountPage;
import org.example.NewsletterSubscriptionPage;
import org.example.RegisterAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class NewsletterSubscriptionTest {
    private WebDriver driver;
    private NewsletterSubscriptionPage newsletterSubscriptionPage;
    private MyAccountPage myAccountPage;
    private final String newsletterSubscriptionURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/newsletter";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        myAccountPage = new MyAccountPage(driver);
        String registerPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
        System.out.println("Navigate to " + registerPageURL);
        newsletterSubscriptionPage = new NewsletterSubscriptionPage(driver);
        createAccount();

    }

    @Test
    public void changeNewsletterSubscriptionYes() {
        driver.get(newsletterSubscriptionURL);
        newsletterSubscriptionPage.clickSubscribeRadioButtonYes();
        newsletterSubscriptionPage.clickContinueButton();
        Assert.assertEquals(myAccountPage.successAlert(), "Success: Your newsletter subscription has been successfully updated!");
    }

    @Test
    public void changeNewsletterSubscriptionNo() {
        driver.get(newsletterSubscriptionURL);
        newsletterSubscriptionPage.clickSubscribeRadioButtonNo();
        newsletterSubscriptionPage.clickContinueButton();
        Assert.assertEquals(myAccountPage.successAlert(), "Success: Your newsletter subscription has been successfully updated!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void createAccount() {
        String registerURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
        driver.get(registerURL);
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        System.out.println("Creating new account to be use in tests...Done");
    }
}