package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentManager;

import java.io.IOException;

public class DashboardTest {
    private WebDriver driver;
    public LoginPage loginPage;

    public DashboardPage dashboardPage;

    @BeforeClass()
    public void setup() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDirectory+"/src/chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
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


        driver.manage().window().maximize();
        loginPage.SelectCountry();
        loginPage.SelectedCountry();
    }

    @Test(dataProvider = "excelDataUsername", dataProviderClass = ExcelUtils.class, priority = 2)
    public void loginUser(String username) throws InterruptedException {

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
    }

    @Test(priority = 4)
    public void dashboardHeading() throws InterruptedException {
        String headingDashboard= dashboardPage.dashboardHeading();
        ExtentManager.createTest("Dashboard loaded successfully");
        if(headingDashboard.equals("Rates dashboard")){
            ExtentManager.getTest().pass("Dashboard heading is getting displayed "+headingDashboard);
        }else{
            ExtentManager.getTest().fail("Dashboard heading is getting displayed "+headingDashboard);
        }
    }

    @Test(priority = 5)
    public void headersLogout(){
        String headingDashboard= dashboardPage.logoutButton();
        ExtentManager.createTest("Dashboard is having logout");
        if(headingDashboard.equals("Log out")){
            ExtentManager.getTest().pass("Logout option is getting displayed "+headingDashboard);
        }else{
            ExtentManager.getTest().fail("Logout option is getting displayed "+headingDashboard);
        }
    }


    @Test(priority = 6)
    public  void tableDataValidation(){
        String filteredValue= dashboardPage.draftFilter();
        ExtentManager.createTest("Dashboard listing page rates filter");
        if(filteredValue.equals("Draft")){
            ExtentManager.getTest().pass("DraftOption is visible :"+filteredValue);
        }else {
            ExtentManager.getTest().fail("Fileter is not visble :"+filteredValue);
        }

    }

    @Test(priority = 7)
    public void testDraftFilter(){
        dashboardPage.draftFilter();
        dashboardPage.draftFilterClick();
        dashboardPage.filteredValue();
    }
    @AfterClass
    public void teardown() {
        if (driver != null) {
          //  driver.quit();
        }
        ExtentManager.flush();
    }
}
