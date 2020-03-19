package org.SDET.interviewtest.common.ui;

import org.SDET.interviewtest.common.endpoint.Endpoint;
import org.SDET.interviewtest.common.endpoint.Host;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import java.util.List;


public class BaseUI {
    private WebDriver driver;
    private Browser browser = Browser.valueOf(System.getProperty("browser", Browser.CHROME.name()));

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case CHROME:
                default:
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
                    driver = new ChromeDriver();
                    driver.manage().window().setSize(new Dimension(1440, 1500));
                    break;
            }
        }
        return driver;
    }


    public void get(Endpoint endpoint) {
        getDriver();
        driver.get(Host.STARWARS_API.getURL() + endpoint.getEndpoint());
    }

    public void get(WebDriver driver, Endpoint endpoint) {
        getDriver();
        driver.get(Host.STARWARS_COM.getURL() + endpoint.getEndpoint());
    }

    public static void waitFor(WebDriver driver, WebElement... webElements) {
        waitFor(driver, 10, webElements);
    }

    public static void waitFor(WebDriver driver, long timeout, WebElement... webElements) {
        for (WebElement element : webElements) {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
        }
    }

    public static void waitForClickable(WebDriver driver, WebElement... webElements) {
        waitForClickable(driver, 10, webElements);
    }

    public static void waitForClickable(WebDriver driver, long timeout, WebElement... webElements) {
        for (WebElement element : webElements) {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public static boolean elementIsPresent(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }

    public static void sendKeys(WebDriver driver, WebElement element, String text) {
        waitFor(driver, element);
        scrollTo(driver, element);
        waitForClickable(driver, element);
        element.click();
        element.sendKeys(text);
    }

    public static void click(WebDriver driver, WebElement element) {
        waitFor(driver, element);
        scrollTo(driver, element);
        waitForClickable(driver, element);
        element.click();
    }

    public static List<WebElement> findElementByText(WebDriver driver, String text){
        List<WebElement> elements = driver.findElements(By.xpath("//p[contains(text(), \'" + text + "\')]"));
        if(elements.size() == 0){
            elements = findDivElementByText(driver, text);
        }
        return elements;
    }

    private static List<WebElement> findDivElementByText(WebDriver driver, String text){
        return driver.findElements(By.xpath("//div[contains(text(), \'" + text + "\')]"));
    }

    public static void scrollTo(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    protected String getModelId(){
        String url = getDriver().getCurrentUrl();
        return url.substring(url.indexOf("id=") + 3).split("&")[0];
    }

}
