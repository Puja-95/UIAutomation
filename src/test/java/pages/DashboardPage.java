package pages;

import BasePackage.BasePage;
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

    public String filteredValue() {
        System.out.println(filteredDraftElement.getText());
        return filteredDraftElement.getText();
    }

}
