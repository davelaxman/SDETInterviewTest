package org.SDET.interviewtest.common.ui;

import org.SDET.interviewtest.common.endpoint.Endpoint;
import org.SDET.interviewtest.common.endpoint.Host;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import java.util.List;


public class BaseUI {
    private WebDriver driver;
    private Browser browser = Browser.valueOf(System.getProperty("browser", Browser.EDGE.name()));

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        String driverPath = getDriverPath(browser.toString());

        if (driver == null) {
            switch (browser) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    driver = new ChromeDriver();
                    driver.manage().window().setSize(new Dimension(1440, 1500));
                    break;
                case EDGE:
                    System.setProperty("webdriver.edge.driver", driverPath);
                    driver = new EdgeDriver();
                    driver.manage().window().setSize(new Dimension(1440, 1500));
                    break;
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", driverPath);
                    driver = new FirefoxDriver();
                    driver.manage().window().setSize(new Dimension(1440, 1500));
                    break;
                case SAFARI:
                    driver = new SafariDriver();
                    driver.manage().window().setSize(new Dimension(1440, 1500));
                    break;
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
    public static String getDriverPath(String browser) {
        String driverPath = "";
        String chromeDriverPath;
        String edgeDriverPath;
        String firefoxDriverPath;
        String safariDriverPath;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            os = "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            os = "Linux";
        } else if (os.contains("mac")) {
            os = "Mac";
        } else {
            os = "Others";
        }

        switch (os) {
            case "Windows":
                chromeDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/windows/chrome/chromedriver.exe";
                edgeDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/windows/edge/msedgedriver.exe";
                firefoxDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/windows/firefox/geckodriver.exe";
                safariDriverPath = "NOT WINDOWS COMPATIBLE";
                break;
            case "Linux":
                chromeDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/linux/chromedriver.exe";
                edgeDriverPath = "NOT LINUX COMPATIBLE";
                firefoxDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/firefox/geckodriver.exe";
                safariDriverPath = "NOT LINUX COMPATIBLE";
                break;
            case "Mac":
                chromeDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/mac/chrome/chromedriver";
                edgeDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/mac/edge/msedgedriver";
                firefoxDriverPath = System.getProperty("user.dir") + "/src/main/java/org/SDET/interviewtest/common/drivers/mac/firefox/geckodriver";
                safariDriverPath = "TODO";
                break;
            default:
                chromeDriverPath = "NOT DEFINED";
                edgeDriverPath = "NOT DEFINED";
                firefoxDriverPath = "NOT DEFINED";
                safariDriverPath = "NOT DEFINED";
                break;
        }

        switch (browser){
            case "CHROME":
                driverPath = chromeDriverPath;
                break;
            case "EDGE":
                driverPath = edgeDriverPath;
                break;
            case "FIREFOX":
                driverPath = firefoxDriverPath;
                break;
            case "SAFARI":
                driverPath = safariDriverPath;
                break;
            default:
                driverPath = chromeDriverPath;
                break;
        }
        return driverPath;
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
