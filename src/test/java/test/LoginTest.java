package test;


import org.testng.Assert;

import org.testng.annotations.*;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExcelUtils;
import utils.ExtentManager;



import java.io.IOException;


public class LoginTest {
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
        } else if ("Stage".equalsIgnoreCase(environment)) {
            baseUrl="https://dt-admin-stage.ldsvcplatform.com/en/login";
        } else {
            throw new IllegalArgumentException("Invalid environment: " + environment);
        }
        ExtentManager.createTest("Environement selected successfully");
        if(environment.equals("Dev")){
            ExtentManager.getTest().pass("Environement selected successfully :"+ environment);
        } else if (environment.equals("Preprod")) {
            ExtentManager.getTest().pass("Environement selected success fully :"+environment);
        } else if (environment.equals("Stage")) {
            ExtentManager.getTest().pass("Environmenent selected successfully :"+environment);

        } else {
            ExtentManager.getTest().fail("Environement not passed correctly :"+environment);
        }

        driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();


    }

    @Test(priority=2, dataProvider = "countryData", dataProviderClass = ExcelUtils.class)
    public void selectCountryTest(String country) throws IOException, InterruptedException {

        loginPage.SelectCountry();
        String countrySelected=loginPage.SelectCountry();
        ExtentManager.createTest("Country selected successfully");
        if(countrySelected.equals(loginPage.ExpectedCountry())){
            ExtentManager.getTest().pass("Country is present :"+countrySelected);
        }else{
            ExtentManager.getTest().fail("country is not present :"+countrySelected);
        }


    }

    @Test(priority=3, dataProvider = "countryData", dataProviderClass = ExcelUtils.class)
    public void loginByUsername(String country)  {
        ExtentManager.createTest("Username Page Element Validation Test");

        try {

            loginPage.SelectedCountry();
            ExtentManager.getTest().info("Country selected successfully");

            if (loginPage.isEnterUsernameScreenDisplayed()) {
                ExtentManager.getTest().pass("Username page element appeared successfully.");
            } else {
                ExtentManager.getTest().fail("username enter page element did not appear.");
            }
        } catch (Exception e) {
            ExtentManager.getTest().error("An error occurred: " + e.getMessage());
        }



    }

      @Test(dataProvider = "excelDataUsername", dataProviderClass = ExcelUtils.class, priority = 4)
    public void loginUser(String username) throws InterruptedException {
        loginPage.Adminlogin(username);

          ExtentManager.createTest("Login by password Button Click Validation Test" );

          try {
              if (loginPage.isLoginByPasswordClickable()) {
                  ExtentManager.getTest().pass("Login by password Button is clickable.");
                  loginPage.loginByPasswordclickButton();
                  ExtentManager.getTest().info("Button clicked.");
                  if (loginPage.isPasswordElementDisplayed()) {
                      ExtentManager.getTest().pass("Password entering element appeared after clicking the button.");
                  } else {
                      ExtentManager.getTest().fail("Expected element did not appear.");
                  }
              } else {
                  ExtentManager.getTest().fail("Button is not clickable.");
              }
          } catch (Exception e) {
              ExtentManager.getTest().error("An error occurred: " + e.getMessage());
          }
    }

    @Test(dataProvider = "excelDataPassword", dataProviderClass = ExcelUtils.class, priority = 5)
    public void loginByPasswordEnabled(String password){
        loginPage.adminPassword(password);
        ExtentManager.createTest("Confirm and Click button Validation Test" );
        try {
            if (loginPage.isButtonClickable()) {
                ExtentManager.getTest().pass("Button is clickable.");
                loginPage.clickButton();
                ExtentManager.getTest().info("Button clicked.");
                if (loginPage.isNewElementDisplayed()) {
                    ExtentManager.getTest().pass("language selection element appeared after clicking the button.");
                } else {
                    ExtentManager.getTest().fail("Expected element did not appear.");
                }
            } else {
                ExtentManager.getTest().fail("Button is not clickable.");
            }
        } catch (Exception e) {
            ExtentManager.getTest().error("An error occurred: " + e.getMessage());
        }
    }

  @Test(priority = 6)
    public void selectLanguage(){
        loginPage.SelectLanguage();

      String payAsYouGoSelected=loginPage.optionSelected();
      ExtentManager.createTest("Test Pay as you go mode Selection");
      if(payAsYouGoSelected.equals("Rates")){
          ExtentManager.getTest().pass("Pay as you go selected successfully :"+payAsYouGoSelected);
      }else{
          ExtentManager.getTest().fail("Pay as you go is failed successfull :"+payAsYouGoSelected);
      }

  }


     @Test(priority = 7)
    public void selectRates(){
        loginPage.selectRates();
        String RatesSelected= loginPage.optionSelected();
         ExtentManager.createTest("Test Rates Selection");
         if(RatesSelected.equals("Pay as you go")) {
           // Assert.assertEquals("Pay as you go", loginPage.optionSelected());

            ExtentManager.getTest().pass("Rates Selected Successfully : "+RatesSelected);
        }else{
            ExtentManager.getTest().fail("Rates not selected Failed :"+RatesSelected);
        }
    }

    @Test(priority = 8)
    public void selectPayasyougo(){

        loginPage.selectPayasyougo();
        ExtentManager.createTest("Validate Successful Login");
        boolean dashboardVerified=loginPage.dashboardPage();
        if(dashboardVerified==true){
            ExtentManager.getTest().pass("Login was successful: " + dashboardVerified);
        }else {
            ExtentManager.getTest().fail("Login failed. Expected message: " +dashboardVerified);
        }
        }

    @AfterClass
    public void teardown() {
        if (driver != null) {
           // driver.quit();
        }
        ExtentManager.flush();
    }
}
