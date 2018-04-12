package com.logicbusinesstst;

import com.logicbusinesstst.pages.IndexPage;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static WebDriver driver;

    @org.junit.BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\FrontDev\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://market.yandex.ru/");
    }

    @org.junit.Test
    public void Yandex() {

        String findQuery = "Iphone 8";

        WebElement searchField = driver.findElement(By.id("header-search"));
        searchField.sendKeys("Iphone 8");
        WebElement searchButton = driver.findElement(By.cssSelector(".search2__button > button"));
        searchButton.click();

        List<WebElement> goods = driver.findElements(By.cssSelector(".n-snippet-cell2__header > .n-snippet-cell2__title"));

        for(int i = 0; i < goods.size(); i++) {
            if(goods.get(i).getText().toLowerCase().contains((findQuery).toLowerCase())) {
                System.out.println("Good news everyone! Match found!");
            }
        }

        WebElement sortButton = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[3]/a"));
        sortButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<WebElement> sortedGoods = driver.findElements(By.cssSelector("body > div.main > div:nth-child(5) > div.layout.layout_type_search.i-bem > div.layout__col.i-bem.layout__col_search-results_normal > div.n-filter-applied-results.metrika.b-zone.i-bem.n-filter-applied-results_js_inited.b-zone_js_inited > div > div.n-snippet-list.n-snippet-list_type_grid.snippet-list_size_3.metrika.b-zone.b-spy-init.i-bem.metrika_js_inited.snippet-list_js_inited.b-spy-init_js_inited.b-zone_js_inited > div:nth-child(1) > div.n-snippet-cell2__body > div.n-snippet-cell2__price > div > a"));
        WebElement firstGood = sortedGoods.get(0);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", firstGood);

        List<WebElement> bestOfferStore = driver.findElements(By.cssSelector(".n-snippet-card__shop > a "));
        System.out.println(bestOfferStore.get(0).getText());

        List<WebElement> bestOfferPrice = driver.findElements(By.cssSelector(".snippet-card__price > div "));
        System.out.println(bestOfferPrice.get(0).getText());

        IndexPage indexPage = new IndexPage(driver);
        indexPage.searchForItem("Iphone 8");
    }

    @AfterClass
    public static void tearDown() {


    }

}
