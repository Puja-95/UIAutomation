package pages;


import BasePackage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    WebElement targetElement;
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

    public String SelectCountry() throws InterruptedException {
        Thread.sleep(3000);
        wait.scrollByPixels(100);
        countr = country.replaceAll("[\\[\\]]", "");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement scrollableBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_countryGrid_1b7ra_77 MuiBox-root css-0']/child::div")));

       //WebElement scrollableBox = driver.findElement(By.xpath("//div[@class='_countryGrid_1b7ra_77 MuiBox-root css-0']/child::div"));
        targetElement = driver.findElement(By.xpath("//span[text()='"+countr+"']"));

        // Scroll until the target element is in view
        while (!targetElement.isDisplayed()) {
            // Scroll down inside the box by 250 pixels
            js.executeScript("arguments[0].scrollTop += 250;", scrollableBox);
        }

        WebElement selectCountry=driver.findElement(By.xpath("//span[text()='"+countr+"']"));
        return selectCountry.getText();
    }

    public String ExpectedCountry(){
        String countr = country.replaceAll("[\\[\\]]", "");
        return countr;
    }


    public void SelectedCountry() throws IOException {
      //  String country= Arrays.toString(ExcelUtils.provideDataCountryName());
       // WebElement selectCountry=driver.findElement(By.xpath("//span[text()='"+countr+"']"));
        targetElement.click();
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

    public void loginButtonClick() throws InterruptedException {
        Thread.sleep(2000);
        loginButton.click();
        wait.scrollByPixels(200);

    }

    public boolean loginButtonEnabaled(){

        return confirmButton.isEnabled();
    }

    public void confirmAndClick(){
        confirmButton.click();
    }

    public boolean dashboardPage(){
        boolean logoutIsVisible=logoutbutton.isDisplayed();
        return logoutIsVisible;
    }
//this newly added code for more validation of password*****************************
    public boolean isButtonClickable() throws InterruptedException {
        Thread.sleep(3000);
        return confirmButton.isEnabled();
    }

    // Method to click the button
    public void clickButton() throws InterruptedException {
        Thread.sleep(3000);
        confirmButton.click();
    }

    public boolean isLoginByPasswordClickable() throws InterruptedException {
        Thread.sleep(3000);
        return loginButton.isEnabled();
    }

    // Method to click the button
    public void loginByPasswordclickButton() throws InterruptedException {
        Thread.sleep(3000);
        loginButton.click();
    }


    // Method to check if the new element appeared
    public boolean isNewElementDisplayed() {
        return languageSelect.isDisplayed();
    }

    public boolean isPasswordElementDisplayed() {
        return passwordField.isDisplayed();
    }
}
