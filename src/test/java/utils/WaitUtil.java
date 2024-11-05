package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {
    private WebDriver driver;
    private WebDriverWait explicitWait;
    private Duration implicitWaitDuration;

    // Constructor to initialize WebDriver and waits
    public WaitUtil(WebDriver driver, Duration explicitTimeout, Duration implicitTimeout) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, explicitTimeout);
        this.implicitWaitDuration = implicitTimeout;
        driver.manage().timeouts().implicitlyWait(implicitTimeout);
    }

    // Method to set implicit wait duration dynamically if needed
    public void setImplicitWait(Duration timeout) {
        this.implicitWaitDuration = timeout;
        driver.manage().timeouts().implicitlyWait(timeout);
    }

    // Method for waiting until an element is visible
    public WebElement waitForElementVisibility(By locator) {
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method for waiting until an element is clickable
    public WebElement waitForElementToBeClickable(WebElement element) {
        return explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Method to scroll to a specific WebElement
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Method to scroll down by a specific number of pixels
    public void scrollByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    // Method to scroll to the bottom of the page
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Method to scroll to the top of the page
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
}
