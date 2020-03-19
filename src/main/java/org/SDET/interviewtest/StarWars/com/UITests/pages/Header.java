package org.SDET.interviewtest.StarWars.com.UITests.pages;

import org.SDET.interviewtest.common.ui.BaseUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BaseUI {
    WebDriver driver;

    @FindBy(id="nav-sw-logo")
    private WebElement logo;

    @FindBy(id="nav-search-input")
    private WebElement searchField;

    @FindBy(id="nav-search-icon")
    private WebElement searchButton;

    public Header(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLogo(){
        logo.click();
    }

    public void clickSearchTextBox(){
        BaseUI.waitFor(driver, searchField);
        searchField.click();
    }

    public void searchForText(String text){
        BaseUI.sendKeys(this.driver,searchField,text);
        searchButton.click();
        BaseUI.waitFor(driver, searchButton);
    }

}

