package com.infinitecampus.tests;

import com.aventstack.extentreports.Status;
import com.infinitecampus.pages.GooglePage;
import com.infinitecampus.testbase.BaseTest;
import com.infinitecampus.utils.ConstantsLibUtil;
import com.infinitecampus.utils.ExtentReport;
import com.infinitecampus.utils.ScreenshotUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

public class SearchPageGoogleTest extends BaseTest {

    private String expectedLinkText = "https://www.infinitecampus.com/company/careers";;

    @BeforeMethod
    public void testSetup(Method method) {
        System.setProperty(ConstantsLibUtil.CHROMEDRIVER_KEY, ConstantsLibUtil.CHROMEDRIVER_PATH);
        extentReport.createTestCase(method.getName().replace("_", " "));

        extentReport.addLog(Status.INFO, "Instantiate Chrome Driver");
        driver = new ChromeDriver();

        extentReport.addLog(Status.INFO, "Instantiate Google Page");
        googlePage = new GooglePage(driver);
        screenshotUtil = new ScreenshotUtil();

        extentReport.addLog(Status.INFO, "Set Implicitly wait time");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        extentReport.addLog(Status.INFO, "Maximize Window");
        driver.manage().window().maximize();

        extentReport.addLog(Status.INFO, "Launching google app");
        driver.get(ConstantsLibUtil.GOOGLE_URL);
    }

    @Test
    public void verify_Campus_Career_Page_Is_First_Result() {

        extentReport.addLog(Status.INFO, "Entering 'infinite campus careers' text in google search");
        googlePage.enterGoogleSearchText(searchText);
        extentReport.addLog(Status.INFO, "Click on Search Button");
        googlePage.clickOnSubmit();

        List<WebElement> resultLinks = googlePage.resultsLinks();
        String firstResultLink = resultLinks.get(0).getAttribute("href");
        Assert.assertTrue(firstResultLink.equals(expectedLinkText));
        extentReport.addLog(Status.PASS, "Campus Career Page is the first on the list" );
        WebElement resultSection = googlePage.getResultSection();
        extentReport.attachImageToReport(resultSection, Status.PASS, "Results");
    }


    @AfterMethod
    public void afterTestMethod(ITestResult testResult) throws InterruptedException {
        if (!testResult.isSuccess()) {
            if (testResult.getName().equals("verify_Campus_Career_Page_Is_First_Result")) {
                extentReport.addLog(Status.FAIL, "Campus Career Page is not the first result");
                WebElement resultSection = googlePage.getResultSection();
                extentReport.attachImageToReport(resultSection, Status.FAIL, "Results");
            }
        }

        driver.quit();
    }

}
