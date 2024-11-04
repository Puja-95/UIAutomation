package test;

import org.testng.Assert;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.YopMailPage;
import testDataProvider.TestDataProvider;

public class LoginTest {

    private WebDriver driver;


    private LoginPage loginPage;

    private YopMailPage yopMailPage;

    @BeforeClass
    public void setup() {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        System.setProperty("webdriver.chrome.driver", userDirectory+"/src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dt-admin-pre-prod.ldsvcplatform.com/en/login");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

   @Test(priority=1)
    public void testSelectCountry() throws InterruptedException {
        loginPage.SelectCountry();
        Assert.assertEquals("Austria", loginPage.SelectCountry());
       loginPage.SelectedCountry();
    }



    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, priority = 2)
    public void loginUser(String username){

        System.out.println(loginPage.loginButton());
        Assert.assertEquals(true, loginPage.loginButton());
        loginPage.Adminlogin(username);
        loginPage.loginButtonClick();


    }

    @Test(dataProvider = "loginDataPassword", dataProviderClass = TestDataProvider.class, priority = 3)
    public void loginByPasswordEnabled(String password){
        loginPage.adminPassword(password);
        Assert.assertEquals(true, loginPage.loginButtonEnabaled());
        loginPage.confirmAndClick();
    }

  @Test(priority = 4)
    public void selectLanguage(){
        loginPage.SelectLanguage();
      Assert.assertEquals("Rates", loginPage.optionSelected());

  }


     @Test(priority = 5)
    public void selectRates(){
        loginPage.selectRates();
        Assert.assertEquals("Pay as you go", loginPage.optionSelected());
    }

    @Test(priority = 6)
    public void selectPayasyougo(){
        loginPage.selectPayasyougo();
        Assert.assertEquals("Log out", loginPage.dashboardPage());
    }

    @AfterClass
    public void teardown() {
        //driver.quit();
    }
}
