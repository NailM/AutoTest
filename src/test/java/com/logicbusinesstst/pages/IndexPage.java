package com.logicbusinesstst.pages;

import com.logicbusinesstst.entity.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class IndexPage {

    public WebDriver driver;

    public IndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "header-search")
    private WebElement searchField;

    @FindBy(css = ".search2__button > button")
    private WebElement searchButton;

    public List<Item> searchForItem(String itemName) {

        searchField.sendKeys(itemName);
        searchButton.click();

        List<WebElement> items = driver.findElements(By.cssSelector(".n-snippet-cell2__header > .n-snippet-cell2__title"));
        List<WebElement> itemsPrice = driver.findElements(By.cssSelector(".n-snippet-cell2__main-price > a > .price"));

        List<Item> searchResult = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            searchResult.add(new Item(items.get(i).getText(), itemsPrice.get(i).getText()));
        }

        System.out.println(searchResult);

        return searchResult;


    }


}
