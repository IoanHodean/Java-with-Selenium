import org.example.AccountCreatedPage;
import org.example.ChangePasswordPage;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    private String loginPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";
    private String changePasswordURL="https://ecommerce-playground.lambdatest.io/index.php?route=account/password";

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();

        System.out.println("Navigate to " + loginPageURL);
        driver.get(loginPageURL);
        changePasswordPage=new ChangePasswordPage(driver);
        loginPage= new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.insertPassword("idunno");
        loginPage.clickButton();
    }
    @Test
    public void ChangePasswordWithValidCredentials(){
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idunno");
        changePasswordPage.passwordConfirmInput("idunno");
        changePasswordPage.clickContinueButton();
        AccountCreatedPage accountCreatedPage=new AccountCreatedPage(driver);
        String expected=accountCreatedPage.getSuccessText();
        Assert.assertEquals(expected, "Success: Your password has been successfully updated.");
    }
    @Test
    public void ChangePasswordWithMismatchingInput(){
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idunno");
        changePasswordPage.passwordConfirmInput("idunnos");
        changePasswordPage.clickContinueButton();
        String expected=changePasswordPage.getMismatchPasswordErrorText();
        Assert.assertEquals(expected, "Password confirmation does not match password!");
    }
    @Test
    public void ChangePasswordWithInvalidInsufficientInput(){
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idu");
        changePasswordPage.passwordConfirmInput("idu");
        changePasswordPage.clickContinueButton();
        String expected=changePasswordPage. getInvalidInputPasswordErrorText();
        Assert.assertEquals(expected, "Password must be between 4 and 20 characters!");
    }
    @Test
    public void ChangePasswordWithInvalidExceedingInputTest() throws InterruptedException {
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("idubfieqfgbeqgbeqgbqegneqfgqesdgfeaqdge");
        changePasswordPage.passwordConfirmInput("idubfieqfgbeqgbeqgbqegneqfgqesdgfeaqdge");
        changePasswordPage.clickContinueButton();
        Thread.sleep(1000);
        String expected=changePasswordPage. getInvalidInputPasswordErrorText();
        Assert.assertEquals(expected, "Password must be between 4 and 20 characters!");
    }
    @Test
    public void ChangePasswordWithInvalidSymbolsTest(){
        driver.get(changePasswordURL);
        changePasswordPage.passwordInput("#!IKT./31");
        changePasswordPage.passwordConfirmInput("#!IKT./31");
        changePasswordPage.clickContinueButton();
        String expected=changePasswordPage. getInvalidInputPasswordErrorText();
        Assert.assertEquals(expected, "Password must be between 4 and 20 characters!");
    }
    @AfterTest
    public void close(){
        driver.quit();
    }
}
