package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Method to initialize ExtentReports
    public static ExtentReports getExtent() {
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static void createTest(String testName) {
        getExtent(); // Ensure that extent is initialized
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void assertNotPresent(By locator, WebDriver driver, String message) {
        try {
            boolean isPresent = !driver.findElements(locator).isEmpty();
            Assert.assertFalse(isPresent, message);
            test.get().pass(message + " | Element is not present as expected.");
        } catch (AssertionError e) {
            test.get().fail(message + " | Assertion Failed: Element was found.");
            throw e;
        }
    }
}
