package com.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class Listners extends BaseClass implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("FitPeo");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project Name", "Fitpeo");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("TOOL", "Selenium");
        extent.setSystemInfo("QA", "Sonaji");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method can be implemented if needed.
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    public static String takeScreenshot(String screenshotName, WebDriver driver) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy-hhmmss");
        String strDate = formatter.format(date);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/target/" + screenshotName + "_" + strDate + ".jpg";
        try {
            Files.copy(srcFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
