package com.infinitecampus.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ExtentReport {

    private ExtentSparkReporter extentSparkReporter;
    private ExtentReports extentReports;
    private ExtentTest testReport;
    private static ExtentReport report = null;

    private  ExtentReport() {

        extentSparkReporter = new ExtentSparkReporter(ConstantsLibUtil.REPORTS_PATH);
        extentReports = new ExtentReports();
        extentSparkReporter.config().setDocumentTitle("TAE Exercise Report");
        extentSparkReporter.config().setReportName("TAE Exercise Report");
        extentReports.attachReporter(extentSparkReporter);
    }

    public static ExtentReport getInstance() {
        if(report == null) {
            report = new ExtentReport();
        }
        return report;
    }

    public void createTestCase(String testCaseName) {
        testReport = extentReports.createTest(testCaseName);
    }

    public void addLog(Status status, String message) {
        testReport.log(status, message);
    }

    private void attachImage(Status status, String imageText, Media media) {

        testReport.log(status, imageText, media);
    }

    public void attachImageToReport(WebElement element, Status status, String imageText) {
        File source = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(ConstantsLibUtil.IMAGES_PATH + imageText + ".png");
        try {
            FileHandler.copy(source, dest);
        } catch (Exception e) {
            System.out.println("Unable to copy file to destination");
            e.printStackTrace();
        }

        Media media = MediaEntityBuilder.createScreenCaptureFromPath(ConstantsLibUtil.IMAGES_PATH
                + imageText + ".png").build();

        attachImage(status, imageText, media);
    }

    public void flushReport() {
        extentReports.flush();
    }


}
