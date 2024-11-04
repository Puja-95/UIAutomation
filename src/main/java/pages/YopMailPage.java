package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtil;


import java.time.Duration;

public class YopMailPage extends BasePage {
    private WaitUtil wait;
    public YopMailPage(WebDriver driver){
        super(driver);
        this.wait = new WaitUtil(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));

    }

    @FindBy(xpath="//input[@id='login']")
    private WebElement mailTextBox;

    @FindBy(xpath="//button[@title='Check Inbox @yopmail.com']")
    private WebElement arrowClick;


    public String enterMailId() throws InterruptedException {
        System.out.println(wait.waitForElementVisibility(By.xpath("//input[@id='login']")));
        Thread.sleep(10000);
        mailTextBox.sendKeys("admin-lyca");
        arrowClick.click();
        return mailTextBox.getText();
    }
}
