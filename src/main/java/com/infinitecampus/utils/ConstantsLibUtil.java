package com.infinitecampus.utils;


public class ConstantsLibUtil {

    public static final String GOOGLE_URL = "https://www.google.com/";
    public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name").toLowerCase().substring(0,3);
    public static final String CHROMEDRIVER_PATH = PROJECT_DIRECTORY +
            (OS_NAME.equals("win") ? "\\drivers\\chromedriver.exe" : "//drivers//chromedriver");
    public static final String FIREFOXDRIVER_PATH = PROJECT_DIRECTORY +
            (OS_NAME.equals("win") ? "\\drivers\\geckodriver.exe" : "//drivers//geckodriver");
    public static final String EDGERIVER_PATH = PROJECT_DIRECTORY +
            (OS_NAME.equals("win") ? "\\drivers\\msedgedriver.exe" : "//drivers//msedgedriver");
    public static final String CHROMEDRIVER_KEY = "webdriver.chrome.driver";
    public static final String FIREFOXDRIVER_KEY = "webdriver.gecko.driver";
    public static final String EDGEDRIVER_KEY = "webdriver.edge.driver";
    public static final String IMAGES_PATH = PROJECT_DIRECTORY + (OS_NAME.equals("win") ? "\\images\\" : "//images//");
    public static final String REPORTS_PATH = PROJECT_DIRECTORY +
            (OS_NAME.equals("win") ? "\\reports\\" : "//reports//");


}
