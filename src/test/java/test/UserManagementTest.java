
package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserManagementPage;
import utils.ExcelUtils;
import utils.ExtentManager;

import java.io.IOException;

public class UserManagementTest {
    private WebDriver driver;
    public LoginPage loginPage;

    public UserManagementPage userManagementPage;
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

        loginPage.SelectCountry();
        loginPage.SelectedCountry();
    }
    @Test(dataProvider = "excelDataUsername", dataProviderClass = ExcelUtils.class, priority = 2)
    public void loginUser(String username) {
        loginPage.Adminlogin(username);
        loginPage.loginButtonClick();
    }

    @Test(dataProvider = "excelDataPassword", dataProviderClass = ExcelUtils.class, priority = 3)
    public void loginByPasswordEnabled(String password){
        loginPage.adminPassword(password);
        loginPage.confirmAndClick();
        loginPage.SelectLanguage();
        loginPage.selectRates();
        loginPage.selectPayasyougo();
       // loginPage.selectManageRates();
    }

    @Test(priority=5)
    public void manageUserAddButton() throws InterruptedException, IOException {
        userManagementPage=new UserManagementPage(driver);
        boolean addUserButtonDisplayed=userManagementPage.ManageUserbuttonIsDisplayed();
        ExtentManager.createTest("Manage and Add user button clicked successfully");
        if(addUserButtonDisplayed==true){
            ExtentManager.getTest().pass("Manage and Add user button displayed :"+addUserButtonDisplayed);
        }else{
            ExtentManager.getTest().fail("Manage and Add user button displayed :"+addUserButtonDisplayed);
        }
        userManagementPage.addUserbuttonClick();
    }

    @Test(priority=6)
    public void AddUserButton(){
        boolean adduserbuttonDisplay=userManagementPage.addUserButtonIsDisplayed(); ExtentManager.createTest("Add user button clicked successfully");
        ExtentManager.createTest("Add user button clicked successfully");
        if(adduserbuttonDisplay==true){
            ExtentManager.getTest().pass("Add user button displayed :"+adduserbuttonDisplay);
        }else{
            ExtentManager.getTest().fail("Add user button displayed :"+adduserbuttonDisplay);
        }

        userManagementPage.addingUserButtonClick();
    }

  /*  @Test(dataProvider = "excelDataPassword", dataProviderClass = ExcelUtils.class, priority = 7)
    public void enterUserDetails(){

    }*/

    @Test(priority = 7, dataProvider = "enterMailId", dataProviderClass = ExcelUtils.class)
    public void enterMailId(String mailid) {

    userManagementPage.addMail(mailid);
    boolean confirmDetailsButton=userManagementPage.confirmDetailsButtonDisabled();
        ExtentManager.createTest("Confirm details button is disbaled");
        if(confirmDetailsButton==true){
            ExtentManager.getTest().pass("button is disabled :"+confirmDetailsButton);
        }else{
            ExtentManager.getTest().fail("button is disabled :"+confirmDetailsButton);
        }

    }
    @Test(priority = 8, dataProvider = "enterFirstName", dataProviderClass = ExcelUtils.class)
    public void enterFirstName(String firstName) {
    userManagementPage.addFirstName(firstName);
        boolean confirmDetailsButton=userManagementPage.confirmDetailsButtonDisabled();
        ExtentManager.createTest("Confirm details button is disbaled");
        if(confirmDetailsButton==true){
            ExtentManager.getTest().pass("button is disabled :"+confirmDetailsButton);
        }else{
            ExtentManager.getTest().fail("button is disabled :"+confirmDetailsButton);
        }


    }

    @Test(priority = 9, dataProvider = "enterSecondName", dataProviderClass = ExcelUtils.class)
    public void enterLastName(String lastName) {
        userManagementPage.addLastName(lastName);
        boolean confirmDetailsButton=userManagementPage.confirmDetailsButtonDisabled();
        ExtentManager.createTest("Confirm details button is disbaled");
        if(confirmDetailsButton==true){
            ExtentManager.getTest().pass("button is disabled :"+confirmDetailsButton);
        }else{
            ExtentManager.getTest().fail("button is disabled :"+confirmDetailsButton);
        }
    }

    @Test(priority = 10)
    public void ratesselection(){
        userManagementPage.clickRatesButton();

    }

    @Test(priority = 11, dataProvider = "role", dataProviderClass = ExcelUtils.class)
    public void selectRole(String role) throws InterruptedException {
        userManagementPage.selectRole();

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
          //  driver.quit();
        }
        ExtentManager.flush();
    }
}

