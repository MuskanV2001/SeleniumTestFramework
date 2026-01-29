package main.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {


    public static ExtentReports getReportObject(){
        ExtentReports extent = new ExtentReports();

        String reportPath = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);

        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Muskan Vishwakarma");
        return extent;
    }
}
