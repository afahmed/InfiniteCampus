package com.infinitecampus.tests;

import com.aventstack.extentreports.Status;
import com.infinitecampus.pages.GooglePage;
import com.infinitecampus.testbase.BaseTest;
import com.infinitecampus.utils.ConstantsLibUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.time.Duration;

public class CareerOpportunitiesTest extends BaseTest {


    @BeforeMethod
    public void tesSetup(Method method) {

        System.setProperty(ConstantsLibUtil.CHROMEDRIVER_KEY, ConstantsLibUtil.CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get(ConstantsLibUtil.GOOGLE_URL);
        extentReport.createTestCase(method.getName().replace("_", " "));
        extentReport.addLog(Status.INFO, "Instantiate google page");
        googlePage = new GooglePage(driver);
        extentReport.addLog(Status.INFO, "Navigate to google search and click on first link");
        infiniteCampusCareerPage = googlePage.searchAndClickOnFirstResult(searchText);
        extentReport.addLog(Status.INFO, "click on 'View Open Positions' button and navigate to Career " +
                "Opportunities page");
        careerOpportunitiesPage = infiniteCampusCareerPage.clickOnViewOpenPositionsButton();
    }

    @Test
    public void Click_On_View_Open_Positions_Button() {


        SoftAssert softAssert = new SoftAssert();

        extentReport.addLog(Status.INFO, "Click on search button");
        careerOpportunitiesPage.clickOnCareerSearch();
        WebElement searchResultSection = careerOpportunitiesPage.searchResultSection();

        softAssert.assertTrue(careerOpportunitiesPage.verifyAtleastOneOrMoreOpeningsExist());
        if (careerOpportunitiesPage.verifyAtleastOneOrMoreOpeningsExist()) {
            extentReport.addLog(Status.PASS, "Successfully displayed list of current job openings");
            extentReport.attachImageToReport(searchResultSection, Status.PASS, "List of Current Job openings");
        } else {
            extentReport.addLog(Status.FAIL, "There are currently no current job openings");
            extentReport.attachImageToReport(searchResultSection, Status.FAIL, "No Current Job openings" +
                    " to display");
        }

    }

    @AfterMethod
    public void teardown()  {
        driver.quit();
    }
}
