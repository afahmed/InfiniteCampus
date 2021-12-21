package com.infinitecampus.tests;

import com.aventstack.extentreports.Status;
import com.infinitecampus.pages.GooglePage;
import com.infinitecampus.testbase.BaseTest;
import com.infinitecampus.utils.ConstantsLibUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.time.Duration;

public class InfiniteCareerCampusTest extends BaseTest {

    @BeforeMethod
    public void testSetup(Method method) {
        extentReport.createTestCase(method.getName().replace("_", " "));

        System.setProperty(ConstantsLibUtil.CHROMEDRIVER_KEY, ConstantsLibUtil.CHROMEDRIVER_PATH);

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get(ConstantsLibUtil.GOOGLE_URL);
        extentReport.addLog(Status.INFO, "Instantiate google page");
        googlePage = new GooglePage(driver);

        extentReport.addLog(Status.INFO, "Navigate to 'Infinite Careers Campus page");
        infiniteCampusCareerPage = googlePage.searchAndClickOnFirstResult(searchText);

    }

    @Test
    public void verify_Infinite_Campus_Career_has_one_or_more_buttons_View_Open_Positions() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(infiniteCampusCareerPage.verifyPageTitle());
        if (infiniteCampusCareerPage.verifyPageTitle()) {
            extentReport.addLog(Status.PASS, "Successfully navigated to "
                    + infiniteCampusCareerPage.getPageTitle());
        } else {
            extentReport.addLog(Status.FAIL, "Failed to navigate to correct page title");
        }

        softAssert.assertTrue(infiniteCampusCareerPage.verifyOneOrMoreOpenPositionButtonsExist());

        if (infiniteCampusCareerPage.verifyOneOrMoreOpenPositionButtonsExist()) {
            extentReport.addLog(Status.PASS, "One of more buttons named 'View Open Position' exist");
            WebElement viewOpenPositionEl = infiniteCampusCareerPage.getViewOpenPositionButton();
            extentReport.attachImageToReport(viewOpenPositionEl, Status.PASS, "View Open Positions");
        } else {
            extentReport.addLog(Status.FAIL, "Failed to locate buttons with 'View Open Positions'");
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        driver.quit();
    }
}
