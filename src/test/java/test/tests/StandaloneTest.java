package test.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StandaloneTest {

    ExtentReports extent;

    @BeforeTest
    public void configReport(){
        extent = new ExtentReports();

        String reportPath = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Muskan Vishwakarma");
    }

    @Test
    public void initialDemo(){
        extent.createTest("Initial Demo");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        extent.flush();
    }
}
