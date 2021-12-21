package com.infinitecampus.testbase;

import com.aventstack.extentreports.Status;
import com.infinitecampus.pages.CareerOpportunitiesPage;
import com.infinitecampus.pages.GooglePage;
import com.infinitecampus.pages.InfiniteCampusCareerPage;
import com.infinitecampus.utils.ConstantsLibUtil;
import com.infinitecampus.utils.ExtentReport;
import com.infinitecampus.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest {

    protected WebDriver driver;
    protected GooglePage googlePage;
    protected ExtentReport extentReport;
    protected ScreenshotUtil screenshotUtil;
    protected InfiniteCampusCareerPage infiniteCampusCareerPage;
    protected CareerOpportunitiesPage careerOpportunitiesPage;

    protected String searchText = "Infinite campus careers";

    @BeforeClass
    public void beforeClassSetUp() {

        extentReport = ExtentReport.getInstance();
    }

    @AfterClass
    public void testTeardown() throws InterruptedException {;
        extentReport.flushReport();
    }

}
