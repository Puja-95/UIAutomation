/*
package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.IOException;

public class UserManagementTest {
    private WebDriver driver;


    private LoginPage loginPage;
    @BeforeClass
    public void setup() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        System.setProperty("webdriver.chrome.driver", userDirectory+"/src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dt-admin-pre-prod.ldsvcplatform.com/en/login");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginData", dataProviderClass = ExcelUtils.class, priority=0)
    public void loginFunctionalityUserManagement(String username) throws InterruptedException {
        loginPage.SelectCountry();
        loginPage.Adminlogin(username);
      //  loginPage.adminPassword(password);
    }

}
*/
