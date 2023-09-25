import org.example.MyAccountPage;
import org.example.RegisterAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static util.TestUtil.generateRandomEmail;

public class MyAccountTest {
    private WebDriver driver;
    private MyAccountPage myAccountPage;
    RegisterAccountPage registerAccountPage;

    private String myAccountURL="https://ecommerce-playground.lambdatest.io/index.php?route=account/account";
    private String registerURL="https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        myAccountPage=new MyAccountPage(driver);
        registerAccountPage=new RegisterAccountPage(driver);
        }
    @Test
    public void TitleTest(){
       createAccount();
       driver.get(myAccountURL);
        Assert.assertEquals(myAccountPage.getTitle(),"My Account");

    }
    @Test
    public void checkFirstGroupOfButtonsTest(){
        createAccount();
        driver.get(myAccountURL);
        List<WebElement> buttons= myAccountPage.Elements().subList(0,5);
        Assert.assertEquals(buttons.get(0).getText(),"Edit your account information");
        Assert.assertEquals(buttons.get(1).getText(),"Change your password");
        Assert.assertEquals(buttons.get(2).getText(),"Modify your address book entries");
        Assert.assertEquals(buttons.get(3).getText(),"Modify your wish list");
        Assert.assertEquals(buttons.get(4).getText(),"Subscribe / unsubscribe to newsletter");
    }
    @Test
    public void checkSecondGroupOfButtonsTest(){
        createAccount();
        driver.get(myAccountURL);
        List<WebElement> buttons= myAccountPage.Elements().subList(5,11);
        Assert.assertEquals(buttons.get(0).getText(),"View your order history");
        Assert.assertEquals(buttons.get(1).getText(),"Downloads");
        Assert.assertEquals(buttons.get(2).getText(),"Your Reward Points");
        Assert.assertEquals(buttons.get(3).getText(),"View your return requests");
        Assert.assertEquals(buttons.get(4).getText(),"Your Transactions");
        Assert.assertEquals(buttons.get(5).getText(),"Recurring payments");
    }
    @Test
    public void checkThirdGroupOfButtonsTest(){
        createAccount();
        driver.get(myAccountURL);
        Assert.assertEquals(myAccountPage.getAffiliateButtonText(),"Register for an affiliate account");
    }
    public void createAccount(){
        System.out.println("Creating new account to be use in tests...");
        driver.get(registerURL);
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