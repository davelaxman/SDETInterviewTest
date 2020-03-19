package org.SDET.interviewtest.StarWars.com.UITests.tests;

import org.SDET.interviewtest.StarWars.com.UITests.models.SearchResult;
import org.SDET.interviewtest.StarWars.com.UITests.pages.Header;
import org.SDET.interviewtest.StarWars.com.UITests.pages.SearchPage;
import org.SDET.interviewtest.common.endpoint.StarWarsWebsite;
import org.SDET.interviewtest.common.ui.BaseUI;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BasicSearchTest extends BaseUI {

    Header header = new Header(this.getDriver());
    SearchPage searchPage = new SearchPage(this.getDriver());

    @BeforeClass
    public void setup(){
        get(this.getDriver(), StarWarsWebsite.SEARCH);

    }

    @Test
    public void SearchForDarthVader() throws Exception{
        header.searchForText("Darth Vader");
        List<SearchResult> results = searchPage.getSearchResults();
        results.stream().allMatch(x->x.getTitle().equals("Darth Vader"));
    }
}
