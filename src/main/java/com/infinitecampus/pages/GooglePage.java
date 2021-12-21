package com.infinitecampus.pages;

import com.infinitecampus.basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GooglePage extends BasePage {

    private String googlePageTitle = "Google";
    private String googleSearchInput = "xpath:://input[@name='q']";
    private String submitButton = "xpath:://div[@class='lJ9FBc']//input[@value='Google Search']";
    private String googleResultLinks = "xpath:://div[@class='v7W49e']//div//a";
    private String searchResultSection = "id::center_col";

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void enterGoogleSearchText(String searchText) {
        WebElement searchTextEl = getElement(googleSearchInput);
        enterText(searchTextEl, searchText);
    }

    public void clickOnSubmit() {
        WebElement submitBtnEl = getElement(submitButton);
        clickOnElement(submitBtnEl);
    }

    public InfiniteCampusCareerPage searchAndClickOnFirstResult(String searchText) {
        enterGoogleSearchText(searchText);
        clickOnSubmit();
        resultsLinks().get(0).click();
        return new InfiniteCampusCareerPage(driver);
    }

    public List<WebElement> resultsLinks() {
        return getElements(googleResultLinks);
    }

    public WebElement getResultSection() {
        return getElement(searchResultSection);
    }

}
