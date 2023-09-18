import org.example.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class AddAddressTest {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private AddAddressPage addAddressPage;
    private AddressPage addressPage;
    private String registerPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
    private String addAddressURL="https://ecommerce-playground.lambdatest.io/index.php?route=account/address/add";
    @BeforeClass
    public void setUp() throws Exception {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();

        System.out.println("Navigate to " + registerPageURL);
        driver.get(registerPageURL);
        registerAccountPage=new RegisterAccountPage(driver);
        addressPage=new AddressPage(driver);
        addAddressPage=new AddAddressPage(driver);
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

    public void AddAddressWithValidCredentialsMandatoryFields() throws InterruptedException {
        driver.get(addAddressURL);
        addAddressPage.insertFirstName("John");
        addAddressPage.insertLastName("Doe");
        addAddressPage.insertAddressOne("Str. Principala");
        addAddressPage.insertCity("Deva");
        addAddressPage.selectCountry("Romania");
        Thread.sleep(2000);
        addAddressPage.selectRegion("Hunedoara");
        addAddressPage.clickContinueButton();
              Assert.assertEquals(addressPage.getSuccessText(),"Your address has been successfully added");
          }
          @Test
          public void AddAddressWithValidCredentialsAllFields() throws InterruptedException {
              driver.get(addAddressURL);
              addAddressPage.insertFirstName("John");
              addAddressPage.insertLastName("Doe");
              addAddressPage.insertCompany("Biggies");
              addAddressPage.insertAddressOne("Str. Principala");
              addAddressPage.insertAddressTwo("nr. 531");
              addAddressPage.insertCity("Deva");
              addAddressPage.insertPostcode("531412");
              addAddressPage.selectCountry("Romania");
              Thread.sleep(2000);
              addAddressPage.selectRegion("Hunedoara");
              addAddressPage.clickContinueButton();
              Assert.assertEquals(addressPage.getSuccessText(),"Your address has been successfully added");
          }
          @Test
          public void AddAddressWithInvalidInput() throws InterruptedException {
            driver.get(addAddressURL);
              addAddressPage.insertFirstName("31451");
              addAddressPage.insertLastName("31451");
              addAddressPage.insertCompany("31451");
              addAddressPage.insertAddressOne("31451");
              addAddressPage.insertAddressTwo("31451");
              addAddressPage.insertCity("31451");
              addAddressPage.insertPostcode("sdagae");
              // intentionally left blank Country and Region/State dropdowns
              addAddressPage.clickContinueButton();
              Thread.sleep(2000);
              Assert.assertEquals(addAddressPage.getCountryWarning(),"Please select a country!");
          }

          @AfterTest
          public void tearDown(){
            driver.quit();}
}

