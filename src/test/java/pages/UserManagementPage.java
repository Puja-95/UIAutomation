package pages;

import BasePackage.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.ExcelUtils;
import utils.WaitUtil;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class UserManagementPage extends BasePage {
    private WaitUtil wait;

    String role= Arrays.toString(ExcelUtils.addingRole());
    String addedRole = role.replaceAll("[\\[\\]]", "");
    String firstname= Arrays.toString(ExcelUtils.firstName());
    //  System.out.println(firstname+"this is from page");
    String searchKeyword=firstname.replaceAll("[\\[\\]]", "");
    public UserManagementPage(WebDriver driver) throws IOException {
        super(driver);
        this.wait = new WaitUtil(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
    }

    @FindBy(xpath="//button[@type='button']/child::p[text()='Add new User ' or text()='Manage users & teams']")
    private WebElement addUserbuttonElement;

    @FindBy(xpath="//input[@placeholder='Enter the user mail id']")
    private WebElement enterMail;

    @FindBy(xpath="//input[@placeholder='First name']")
    private WebElement enterFirstName;

    @FindBy(xpath="//input[@placeholder='Last name']")
    private WebElement enterLastName;


    @FindBy(xpath = "//p[text()='Rates']")
    private WebElement selectRatesButton;

    @FindBy(xpath="//button[@disabled]/p[text()='Confirm user details']")
    private WebElement confirmDetailsbuttonDisabled;

    @FindBy(xpath="//div[@role='combobox']")
    private WebElement selectRoleDropDown;

    @FindBy(xpath = "//p[text()='Confirm user details' or text()='Yes, delete this user']")
    private WebElement confirmButton;

    @FindBy(xpath="//p[text()='Back to dashboard']")
    private WebElement backToDashboard;


    @FindBy(xpath="//input[@id='search']")
    private WebElement searchElement;

  /*  @FindBy(xpath="//div[text()='cb']")
    private WebElement searchFirstname;*/

    @FindBy(xpath="//td[3]/div/img")
    private WebElement deleteUser;

    @FindBy(xpath="//tbody/tr")
    private  WebElement deletedUserElement;


    @FindBy(xpath="//p[text()='Pay as you go']")
    private WebElement dashboardClick;
    public boolean ManageUserbuttonIsDisplayed() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!addUserbuttonElement.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }
        System.out.println(addUserbuttonElement.isDisplayed());
       return addUserbuttonElement.isDisplayed();
    }

    public void addUserbuttonClick() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        while (!addUserbuttonElement.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 250;");
        }
        Thread.sleep(2000);
        addUserbuttonElement.click();
    }

    public boolean addUserButtonIsDisplayed(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!addUserbuttonElement.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }return addUserbuttonElement.isDisplayed();
    }

    public void addingUserButtonClick() throws InterruptedException {
Thread.sleep(2000);
      wait.scrollToTop();
        addUserbuttonElement.click();
    }

    public boolean confirmDetailsButtonDisabled() throws InterruptedException {Thread.sleep(2000); return confirmDetailsbuttonDisabled.isEnabled();}
    public void addMail(String mailId) throws InterruptedException {enterMail.clear();enterMail.sendKeys(mailId+"1");
        Thread.sleep(2000);
    enterMail.sendKeys(Keys.BACK_SPACE);
    Thread.sleep(2000);}

    public boolean emailIdFieldDisplayed(){
        return enterMail.isDisplayed();
    }
    public void addFirstName(String firstName){enterFirstName.clear();enterFirstName.sendKeys(firstName+"1");
    enterFirstName.sendKeys(Keys.BACK_SPACE);}

    public boolean firstNameEnterBoxDisplayed(){
        return enterFirstName.isDisplayed();
    }
    public void addLastName(String lastName){enterLastName.clear();enterLastName.sendKeys(lastName+"1");
    enterLastName.sendKeys(Keys.BACK_SPACE);}
    public void clickRatesButton(){selectRatesButton.click();}

    public void selectRole() throws InterruptedException {
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!selectRoleDropDown.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }

        selectRoleDropDown.click();

        Thread.sleep(5000);
        WebElement selectRole=driver.findElement(By.xpath("//p[text()='"+addedRole+"']"));
        selectRole.click();
    }

    public boolean confirmClickEnabled(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!confirmButton.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }
        return confirmButton.isEnabled();
    }

    public void confirmClick() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!confirmButton.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }
        confirmButton.click();
        Thread.sleep(2000);
        backToDashboard.click();
    }

    public void searchOption(String firstname) throws IOException {
      //  firstname= Arrays.toString(ExcelUtils.firstName());
      //  System.out.println(firstname+"this is from page");
       // String searchKeyword=firstname.replaceAll("[\\[\\]]", "");
        searchElement.clear();
        searchElement.sendKeys(searchKeyword+"6");
        searchElement.sendKeys(Keys.BACK_SPACE);
    }

    public boolean searchFirstname(String firstname) throws InterruptedException {
        Thread.sleep(2000);
        wait.scrollToTop();
        Thread.sleep(2000);
        boolean searchFirstNameVisible=searchElement.isDisplayed();
return searchFirstNameVisible;
    }

    public void deleteUser() throws InterruptedException {
        Thread.sleep(5000);
        deleteUser.click();
        Thread.sleep(2000);
        confirmButton.click();

    }


}
