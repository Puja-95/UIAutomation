package pages;

import BasePackage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtil;

import java.io.IOException;
import java.time.Duration;

public class UserManagementPage extends BasePage {
    private WaitUtil wait;

    public UserManagementPage(WebDriver driver) throws IOException {
        super(driver);
        this.wait = new WaitUtil(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
    }

    @FindBy(xpath="//button[@type='button']/child::p[text()='Add new User ' or text()='Manage users & teams']")
    private WebElement addUserbuttonElement;

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
}
