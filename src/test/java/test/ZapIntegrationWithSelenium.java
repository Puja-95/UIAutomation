import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZapIntegrationWithSelenium {
    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = ""; // Optional, only if API Key is set in ZAP
    private static final String TARGET_URL = "http://example.com";

    private WebDriver driver;
    private ClientApi zapApi;

    @BeforeClass
    public void setup() {
        // Configure ChromeDriver with ZAP Proxy
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(ZAP_ADDRESS + ":" + ZAP_PORT)
                .setSslProxy(ZAP_ADDRESS + ":" + ZAP_PORT);

        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);

        driver = new ChromeDriver(options);

        // Initialize ZAP Client API
        zapApi = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
    }

    @Test(priority = 1)
    public void performFunctionalTest() {
        // Perform Selenium actions
        driver.get(TARGET_URL);
        System.out.println("Navigated to: " + driver.getCurrentUrl());
    }

    @Test(priority = 2, dependsOnMethods = {"performFunctionalTest"})
    public void runZapSpiderScan() throws ClientApiException, InterruptedException {
        System.out.println("Starting Spider Scan for: " + TARGET_URL);
        zapApi.spider.scan(TARGET_URL, null, null, null, null);

        // Wait for Spider Scan to complete
        while (!"100".equals(zapApi.spider.status(""))) {
            System.out.println("Spider progress: " + zapApi.spider.status("") + "%");
            Thread.sleep(1000);
        }
        System.out.println("Spider Scan completed.");
    }

    @Test(priority = 3, dependsOnMethods = {"runZapSpiderScan"})
    public void runZapActiveScan() throws ClientApiException, InterruptedException {
        System.out.println("Starting Active Scan for: " + TARGET_URL);
        zapApi.ascan.scan(TARGET_URL, "true", "false", null, null, null);

        // Wait for Active Scan to complete
        while (!"100".equals(zapApi.ascan.status(""))) {
            System.out.println("Active Scan progress: " + zapApi.ascan.status("") + "%");
            Thread.sleep(1000);
        }
        System.out.println("Active Scan completed.");
    }

    @AfterClass
    public void tearDown() throws ClientApiException {
        // Generate and print ZAP report
        String report = new String(zapApi.core.htmlreport());
        System.out.println("ZAP Report:\n" + report);

        // Close WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
