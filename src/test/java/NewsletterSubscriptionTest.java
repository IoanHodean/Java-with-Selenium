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
    private RegisterAccountPage registerAccountPage;
    private NewsletterSubscriptionPage newsletterSubscriptionPage;
    private MyAccountPage myAccountPage;
    private String registerPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
    private String newsletterSubscriptionURL="https://ecommerce-playground.lambdatest.io/index.php?route=account/newsletter";
    @BeforeClass
    public void setUp() throws Exception {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        myAccountPage=new MyAccountPage(driver);
        System.out.println("Navigate to " + registerPageURL);
        driver.get(registerPageURL);
        registerAccountPage=new RegisterAccountPage(driver);
        newsletterSubscriptionPage= new NewsletterSubscriptionPage(driver);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

    }
    @Test
    public void changeNewsletterSubscriptionYes(){
        driver.get(newsletterSubscriptionURL);
        newsletterSubscriptionPage.clickSubscribeRadioButtonYes();
        newsletterSubscriptionPage.clickContinueButton();
        Assert.assertEquals(myAccountPage.successAlert(),"Success: Your newsletter subscription has been successfully updated!");
    }
    @Test
    public void changeNewsletterSubscriptionNo(){
        driver.get(newsletterSubscriptionURL);
        newsletterSubscriptionPage.clickSubscribeRadioButtonNo();
        newsletterSubscriptionPage.clickContinueButton();
        Assert.assertEquals(myAccountPage.successAlert(),"Success: Your newsletter subscription has been successfully updated!");
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
