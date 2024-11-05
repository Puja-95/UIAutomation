package test;

import com.aventstack.extentreports.ExtentReports;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExcelUtils;
import utils.TestListener;


import java.io.IOException;

@Listeners(TestListener.class)
public class LoginTest {
    private ExtentReports extent;

    private WebDriver driver;


    public LoginPage loginPage;



    @BeforeClass()
    public void setup() {


        String userDirectory = System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", userDirectory+"/src/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "excelData", dataProviderClass = ExcelUtils.class, priority = 1)
    public void loginTest(String environment) throws InterruptedException, IOException {


        String baseUrl;
        if ("Dev".equalsIgnoreCase(environment)) {
            baseUrl = "https://dt-admin-dev.ldsvcplatform.com/en/login";
        } else if ("Preprod".equalsIgnoreCase(environment)) {
            baseUrl = "https://dt-admin-pre-prod.ldsvcplatform.com/en/login";
        } else {
            throw new IllegalArgumentException("Invalid environment: " + environment);
        }

        driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();


    }

    @Test(priority=2, dataProvider = "countryData", dataProviderClass = ExcelUtils.class)
    public void selectCountryTest(String country) throws IOException {

        loginPage.SelectCountry();
Assert.assertEquals(country, loginPage.SelectCountry());
loginPage.SelectedCountry();

    }



      @Test(dataProvider = "excelDataUsername", dataProviderClass = ExcelUtils.class, priority = 3)
    public void loginUser(String username){


        Assert.assertEquals(true, loginPage.loginButton());
        loginPage.Adminlogin(username);
        loginPage.loginButtonClick();


    }

    @Test(dataProvider = "excelDataPassword", dataProviderClass = ExcelUtils.class, priority = 4)
    public void loginByPasswordEnabled(String password){
        loginPage.adminPassword(password);
        Assert.assertEquals(true, loginPage.loginButtonEnabaled());
        loginPage.confirmAndClick();
    }

  @Test(priority = 5)
    public void selectLanguage(){
        loginPage.SelectLanguage();
      Assert.assertEquals("Rates", loginPage.optionSelected());

  }


     @Test(priority = 6)
    public void selectRates(){
        loginPage.selectRates();
        Assert.assertEquals("Pay as you go", loginPage.optionSelected());
    }

    @Test(priority = 7)
    public void selectPayasyougo(){
        loginPage.selectPayasyougo();
        Assert.assertEquals("Log out", loginPage.dashboardPage(),"Chceking logout on Dashboard screen");
    }

    @AfterMethod
    public void results(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test failed: " + result.getName());
            // Add custom teardown logic for failed tests
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test passed: " + result.getName());
            // Add custom teardown logic for passed tests
        }
    }
    @AfterClass
    public void teardown() {
            driver.quit();
        }
}
