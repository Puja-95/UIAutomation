package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.zaproxy.clientapi.core.ClientApi;
import utils.ExcelUtils;

public class ZAPIntegration {
    private static final String ZAP_HOST = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = ""; // Optional, if ZAP requires an API key

    public static WebDriver setupZapProxy() {
        Proxy zapProxy = new Proxy();
        zapProxy.setHttpProxy(ZAP_HOST + ":" + ZAP_PORT);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(zapProxy);
        return new ChromeDriver(options);
    }

    public static void startSecurityScan(ClientApi zapApi, String targetUrl) throws Exception {

    zapApi.ascan.scan(targetUrl, null, null, null, null,null);
        System.out.println("Spider scan initiated for: " + targetUrl);

        // Wait for the spider to complete
        while (Integer.parseInt(zapApi.spider.status("").toString()) < 100) {
            Thread.sleep(5000);
        }

        zapApi.ascan.scan(targetUrl, "true", "false", null, null, null);
        System.out.println("Active scan initiated for: " + targetUrl);

        // Wait for the active scan to complete
        while (Integer.parseInt(zapApi.ascan.status("").toString()) < 100) {
            Thread.sleep(5000);
        }
      //  String status = zapApi.ascan.status(scanId);
       // System.out.println("Scan Status: " + status);
        System.out.println("Security scan completed.");
    }
}
