package com.infinitecampus.pages;

import com.infinitecampus.basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InfiniteCampusCareerPage extends BasePage {

    private String careersAtCampus = "xpath:://a[contains(text(),'Careers at Campus')]";
    private String viewOpenPositions = "xpath:://a[contains(text(),'View Open Positions')]";
    private String campusCareersPageUrl = "https://www.infinitecampus.com/company/careers";
    private String campusCareerPageTitle = "Careers · Infinite Campus";


    public InfiniteCampusCareerPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCareersAtCampusButton() {
        WebElement element = getElement(careersAtCampus);
        element.click();
    }

    public CareerOpportunitiesPage clickOnViewOpenPositionsButton() {
        WebElement element = getElement(viewOpenPositions);
        element.click();
        return new CareerOpportunitiesPage(driver);
    }

    public boolean verifyOneOrMoreOpenPositionButtonsExist() {
        return verifyElementElementExists(viewOpenPositions);
    }

    public WebElement getViewOpenPositionButton() {
        return getElement(viewOpenPositions);
    }

    public String pageTitle() {
        return getPageTitle();
    }

    public String pageUrl() {
        return getUrl();
    }

    public boolean verifyPageTitle() {
        return getPageTitle().equals(campusCareerPageTitle);
    }

    public boolean getCareerPageUrl() {
        return getUrl().equals(campusCareersPageUrl);
    }


}
