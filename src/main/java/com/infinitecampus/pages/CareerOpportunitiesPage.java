package com.infinitecampus.pages;

import com.infinitecampus.basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CareerOpportunitiesPage extends BasePage {

    private String careerSearchButton = "xpath:://input[@name='tbe_cws_submit']";
    private String resultSection = "xpath:://table[@id='cws-search-results']";
    private String jobsList = "xpath:://table[@id='cws-search-results']//tr";

    public CareerOpportunitiesPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCareerSearch() {
        WebElement element = getElement(careerSearchButton);
        clickOnElement(element);
    }

    public List<WebElement> listOfJobOpenings() {
        return getElements(jobsList);
    }

    public WebElement searchResultSection() {
        return getElement(resultSection);
    }

    public boolean verifyAtleastOneOrMoreOpeningsExist() {
        return listOfJobOpenings().size() > 1;
    }


}
