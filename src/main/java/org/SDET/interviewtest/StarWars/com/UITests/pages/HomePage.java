package org.SDET.interviewtest.StarWars.com.UITests.pages;

import org.SDET.interviewtest.common.ui.BaseUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends BaseUI {
    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(id="overlay")
    private WebElement overlay;


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    public void closePopUpAd(){
       // this.js.executeScript("ga('send','event', '5179172761-138303000579_overlay', 'overlayUserClose');");
        BaseUI.waitFor(this.driver,overlay);
        if(BaseUI.elementIsPresent(driver, By.id("overlay"))){
            System.out.println("overlay found");
        }

    }
}
