package com.infinitecampus.basepage;

import com.infinitecampus.utils.ConstantsLibUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.util.List;

public class CustomDriver {

    protected WebDriver driver;

    public CustomDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This custom driver methods gets string locator and returns a By type
     * string locator examples => id::idlocator, xpth::xpathlocator etc...
     * @param locator
     * @return return By type
     */
    private By getByType(String locator) {
        String byType = locator.split("::")[0];
        locator = locator.split("::")[1];
        By by = null;
        try {
            if (byType.equalsIgnoreCase("id")) {
                by = By.id(locator);
            } else  if (byType.equalsIgnoreCase("name")) {
                by = By.name(locator);
            } else  if (byType.equalsIgnoreCase("class")) {
                by = By.className(locator);
            } else  if (byType.equalsIgnoreCase("tag")) {
                by = By.tagName(locator);
            } else  if (byType.equalsIgnoreCase("css")) {
                by = By.cssSelector(locator);
            }  else  if (byType.equalsIgnoreCase("xpath")) {
                by = By.xpath(locator);
            } else  if (byType.equalsIgnoreCase("linktext")) {
                by = By.linkText(locator);
            } else  if (byType.equalsIgnoreCase("partiallinktext")) {
                by = By.partialLinkText(locator);
            } else {
                System.out.println("locator type not supported");
            }
        } catch (Exception e) {
            System.out.println("Element not found with " + byType + " using " + locator + " locator");
            e.printStackTrace();
        }

        return by;
    }

    /***
     * This method takes a string locator (id::idlocator, xpath::xpathlocator)
     * and returns a WebElement
     * @param locator
     * @return WebElement
     */
    public WebElement getElement(String locator) {
        By by = getByType(locator);
        WebElement element = driver.findElement(by);
        return element;
    }

    /***
     * this custom method takes an element parameter and clicks on it
     * @param element
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }

    /***
     * this custom driver enters a text in UI text field
     * it takes WebElement and String text
     * first input text is cleared, then enters text
     * @param element
     * @param text
     */
    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /***
     * takes an string locator (id::idlocator, xpath::xpathlocator..etc) and returns
     * a collection of elements
     * @param locator
     * @return collection of elements
     */
    public List<WebElement> getElements(String locator) {
        By by = getByType(locator);
        return driver.findElements(by);
    }

    /***
     * this custom wrapper method returns the page title
     * @return
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /***
     * this custom wrapper method returns current page url
     * @return
     */
    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    /**
     * this custom method verifies if one or more element exists on the page
     * @param locator
     * @return true if one or more element is found
     * @return false if no element is found
     */
    public boolean verifyElementElementExists(String locator) {
        List<WebElement> elements = getElements(locator);
        int size = elements.size();
        return size >= 1;
    }
}
