package com.infinitecampus.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ScreenshotUtil {

    /**
     * takes element level screen shot and saves on images folder
     * @param element
     * @param imageName
     */
    public Media elementScreenshot(WebElement element, String imageName) {

        File source = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(ConstantsLibUtil.IMAGES_PATH + imageName + ".png");

        try {
            FileHandler.copy(source, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Media media = MediaEntityBuilder.createScreenCaptureFromPath(ConstantsLibUtil.IMAGES_PATH +
                imageName + ".png").build();
        return media;
    }


}
