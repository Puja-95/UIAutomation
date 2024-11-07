package pages;

import BasePackage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ExcelUtils;
import utils.WaitUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class UserManagementPage extends BasePage {
    private WaitUtil wait;

    String role= Arrays.toString(ExcelUtils.addingRole());
    String addedRole = role.replaceAll("[\\[\\]]", "");
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

  /*  @FindBy(xpath="//p[text()='"+addedRole+"']")
    private WebElement SelectedRole;*/

    public boolean ManageUserbuttonIsDisplayed() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!addUserbuttonElement.isDisplayed()) {
            js.executeScript("arguments[0].scrollTop += 100;");
        }
        System.out.println(addUserbuttonElement.isDisplayed());
       return addUserbuttonElement.isDisplayed();
    }

    public void addUserbuttonClick(){
        addUserbuttonElement.click();
    }

    public boolean addUserButtonIsDisplayed(){
        return addUserbuttonElement.isDisplayed();
    }

    public void addingUserButtonClick(){
        addUserbuttonElement.click();
    }

    public boolean confirmDetailsButtonDisabled(){return confirmDetailsbuttonDisabled.isEnabled();}
    public void addMail(String mailId){enterMail.clear();enterMail.sendKeys(mailId);}
    public void addFirstName(String firstName){enterFirstName.clear();enterFirstName.sendKeys(firstName);}
    public void addLastName(String lastName){enterLastName.clear();enterLastName.sendKeys(lastName);}
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
}
