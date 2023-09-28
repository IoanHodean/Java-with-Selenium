import org.example.LoginPage;
import org.example.LogoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        driver.get(loginPageURL);
        loginPage = new LoginPage(driver);
        loginPage.insertEmail("hodean.ioan@gmail.net");
        loginPage.insertPassword("idunno");
        loginPage.clickButton();
        loginPage.clickLogOutFromColumn();
        logoutPage = new LogoutPage(driver);
    }

    @Test
    public void continueButtonTest() {
        logoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    }

    @AfterTest
    public void close() {
        driver.quit();
    }

}
