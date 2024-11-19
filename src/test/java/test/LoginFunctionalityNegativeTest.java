package test;


import org.testng.annotations.*;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExcelUtils;
import utils.ExtentManager;


import java.io.IOException;


public class LoginFunctionalityNegativeTest {
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
        } else if ("Prod".equalsIgnoreCase(environment)) {
            baseUrl = "https://kiwi-admin.ldsvcplatform.com/en/login";
        }
        else {
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
    public void loginByUsername(String country) throws InterruptedException {
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

    @Test(dataProvider = "enterInvalidUsername", dataProviderClass = ExcelUtils.class, priority = 4)
    public void invalidLoginUser(String mail) throws InterruptedException {
        loginPage.invalidlogin(mail);
        //invalidUser@yopmail.comadmin-lyca@yopmail.com
        loginPage.loginByPasswordclickButton();
        ExtentManager.createTest("Login by password Button Click Validation Test" );
        String errormsg=loginPage.getMessageText();
        try {
            if (errormsg.equals("user not found")) {
                boolean clearing= loginPage.clearText();
                System.out.println(clearing);
                if (clearing==true){
                    ExtentManager.getTest().pass("Useranme text cleared");
                  //  loginPage.clearText();
                }
                }else{
                ExtentManager.getTest().fail("Error msg not displayed");
            }
        } catch (Exception e) {
            ExtentManager.getTest().error("An error occurred: " + e.getMessage());
        }
    }

    @Test(dataProvider = "excelDataUsername", dataProviderClass = ExcelUtils.class, priority = 5)
    public void loginUser(String username) throws InterruptedException, IOException {
      driver.navigate().refresh();

      loginPage.SelectCountry();
      loginPage.SelectedCountry();
      Thread.sleep(2000);
      loginPage.Adminlogin(username);
      loginPage.loginButtonClick();

    }

    @Test(dataProvider = "invalidPassword", dataProviderClass = ExcelUtils.class, priority = 6)
    public void loginByPasswordEnabled(String password) throws InterruptedException {
        Thread.sleep(2000);
        loginPage.adminPassword(password);

        loginPage.confirmAndClick();
        String invalidPasswordMsg= loginPage.invalidPassword();
        ExtentManager.createTest("Login by password Button Click Validation Test" );
        if(invalidPasswordMsg.equals("email/password value is incorrect")){
            ExtentManager.getTest().pass("Password is incorrect");
           // loginPage.validPassword();
        }else {
            ExtentManager.getTest().fail("Button is not clickable.");
        }
        Thread.sleep(5000);

    }


    @Test(priority = 7)
    public void loginByPassword() throws InterruptedException {
        Thread.sleep(2000);
        loginPage.validPassword();
       // loginPage.adminPassword(password);
      //  loginPage.confirmAndClick();

    }

    @Test(dataProvider = "excelDataPassword", dataProviderClass = ExcelUtils.class, priority = 8)
    public void validpassword(String password){
        loginPage.adminPassword(password);
        loginPage.confirmAndClick();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
          //  driver.quit();
        }
        ExtentManager.flush();
    }
}
