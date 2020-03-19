package org.SDET.interviewtest.StarWars.com.UITests.pages;

import org.SDET.interviewtest.common.ui.BaseUI;
import org.SDET.interviewtest.StarWars.com.UITests.models.SearchResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BaseUI {
    WebDriver driver;

    @FindBy(css=".search_results ul")
    private WebElement searchUL;

    List<WebElement> listItems;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<SearchResult> getSearchResults(){
        listItems = this.driver.findElements(By.cssSelector(".search_results > li"));
        List<SearchResult> results = new ArrayList<SearchResult>();
        for (WebElement li : listItems){
            SearchResult searchResult = new SearchResult();
            searchResult.setTitle(li.findElement(By.className("title")).getText());
            searchResult.setDescription(li.findElement(By.cssSelector("div.search-desc p.desc")).getText());
            results.add(searchResult);
        }
        return results;
    }


}
