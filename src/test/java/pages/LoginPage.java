package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelUtils;
import utils.WaitUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class LoginPage extends BasePage {

    private WaitUtil wait;

String country= Arrays.toString(ExcelUtils.provideDataCountryName());
    String countr = country.replaceAll("[\\[\\]]", "");

    @FindBy(xpath="//input[@placeholder='Email address']")
    private WebElement usernameField;

    @FindBy(xpath="//p[text()='Log in by password']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//p[text()='Confirm & Continue' or text()='Add or manage rates']")
    private WebElement confirmButton;

    @FindBy(xpath="//span[text()='EN']")
    private WebElement languageSelect;

    @FindBy(xpath="//h5[text()='Rates' or text()='Pay as you go']")
    private WebElement selectRates;

    //p[text()='Add or manage rates']

    @FindBy(xpath="//p[text()='Log out']")
    private WebElement logoutbutton;

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        this.wait = new WaitUtil(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
    }

    public String SelectCountry()  {
        String countr = country.replaceAll("[\\[\\]]", "");

        WebElement selectCountry=driver.findElement(By.xpath("//span[text()='"+countr+"']"));

        return selectCountry.getText();
    }

    public void SelectedCountry() throws IOException {
      //  String country= Arrays.toString(ExcelUtils.provideDataCountryName());
        WebElement selectCountry=driver.findElement(By.xpath("//span[text()='"+countr+"']"));
        selectCountry.click();
    }

    public void SelectLanguage(){
        languageSelect.click();
    }

    public String optionSelected(){

        return selectRates.getText();
    }

    public void selectRates(){
        selectRates.click();
    }

    public void selectPayasyougo(){
        selectRates.click();
    }

    public  void selectManageRates(){
        confirmButton.click();
    }



    public void Adminlogin(String username) {
     wait.setImplicitWait(Duration.ofSeconds(10));
     wait.scrollByPixels(200);

        usernameField.sendKeys(username);
        
    }

    public void adminPassword(String password){
        passwordField.sendKeys(password);
    }
    public boolean loginButton(){

        wait.scrollByPixels(100);

        return loginButton.isEnabled();
    }

    public void loginButtonClick(){
        loginButton.click();
        wait.scrollByPixels(200);

    }

    public boolean loginButtonEnabaled(){

        return confirmButton.isEnabled();
    }

    public void confirmAndClick(){
        confirmButton.click();
    }

    public String dashboardPage(){
        return logoutbutton.getText();
    }
}
