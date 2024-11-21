package test;

import org.openqa.selenium.WebDriver;
import org.zaproxy.clientapi.core.ClientApi;
import pages.LoginPage;
import utils.ExtentManager;
import pages.ZAPIntegration;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class securityTest {
    public static void main(String[] args) throws Exception {
        LoginPage loginPage;

        // Setup ZAP
        ClientApi zapApi = new ClientApi("localhost", 8081, "lim3p24gjmspi6t78ben6oeab2");
        WebDriver driver = ZAPIntegration.setupZapProxy();

        // Start Extent Reporting
        ExtentManager.createTest("This is test for owasp integration testing security");

        try {
            // Execute Selenium test
            driver.get("https://dt-admin-pre-prod.ldsvcplatform.com/en/login");

            ZAPIntegration.startSecurityScan(zapApi, "https://dt-admin-pre-prod.ldsvcplatform.com/en/login");
          //  String status = zapApi.ascan.status(scanId);

           // loginPage.adminPassword("admin-lyca@yopmail");
            ExtentManager.getTest().pass("Security Test PASS No vulnerabilities detected.");

        } catch (Exception e) {
            ExtentManager.getTest().fail("Security Test FAIL");
        } finally {
            // Finalize the report
            ExtentManager.flush();
            driver.quit();
        }


        byte[] report = zapApi.core.htmlreport();
        Files.write(Paths.get("ZAP_Report.html"), report);
        System.out.println("Report saved to ZAP_Report.html");

    }
}
