package pages;

import BasePackage.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtil;

import java.io.IOException;
import java.time.Duration;

public class DashboardPage extends BasePage {
    private WaitUtil wait;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtil(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
    }

    @FindBy(xpath = "//p[text()='Rates dashboard' or text()='Log out']")
    private WebElement logoutElement;

    @FindBy(xpath = "//p[text()='Rates dashboard']")
    private WebElement headingDashboardElement;

    @FindBy(xpath = "//p[text()='Draft']")
    private WebElement draftFilterElement;

    @FindBy(xpath = "//tbody/tr/td/div/p[text()='Draft']")
    private WebElement filteredDraftElement;

    @FindBy(xpath="//button/span[text()=0][1]")
    private WebElement draftNullFilter;

    public String dashboardHeading() throws InterruptedException {
        // Thread.sleep(2000);
        System.out.println(headingDashboardElement.getText());
        Thread.sleep(2000);
        return headingDashboardElement.getText();
    }

    public String logoutButton() {
        System.out.println(logoutElement.getText());
        return logoutElement.getText();
    }

    public String draftFilter() {
        System.out.println(draftFilterElement.getText());
        return draftFilterElement.getText();
    }

    public void draftFilterClick() {
        draftFilterElement.click();
    }

    public String filteredValue() throws InterruptedException {
        wait.scrollByPixels(100);
        Thread.sleep(5000);
        try {
            if (filteredDraftElement.isDisplayed()) {
                return filteredDraftElement.getText();
            } else {
                return "0";
            }
        } catch (NoSuchElementException e) {
            return "0";  // Element not found, return "0" as default
        }
    }

  /*  public boolean filteredvalueDisplaye(){

        return filteredDraftElement.isDisplayed();
    }*/

    public String zeroElement(){

        return draftNullFilter.getText();
    }
}
