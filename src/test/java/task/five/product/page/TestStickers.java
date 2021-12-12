package task.five.product.page;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class TestStickers extends TestBase {


    public String assertUrl = "http://localhost/litecart/en/";

    @Test
    public void GoogleChrome(){
        startGC();
        driver.navigate().to("http://localhost/litecart");
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
        IsEqualsInfo();
        stop();
    }
    @Test
    public void IE(){
        startIE();
        driver.navigate().to("http://localhost/litecart");
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
        IsEqualsInfo();
        stop();
    }
    @Test
    public void FireFox(){
        startFF();
        driver.navigate().to("http://localhost/litecart");
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
        IsEqualsInfo();
        stop();
    }

    public void IsEqualsInfo() {
        List<WebElement> items = getItems();
        int size = items.size();
        //
        Set<Point> loc = new HashSet<>();
        //
        assertTrue(size > 0);
        for (int i = 0; i < size; i++) {
            WebElement curItem = items.get(i);
            List<String> storePageProduct = GetOptionsFromStorePageWithPriceValidation(curItem);
            curItem.click();
            curItem = driver.findElement(By.id("box-product"));
            List<String> mainPageProduct = GetOptionsFromProductPageWithPriceValidation(curItem);
            assertTrue(storePageProduct.equals(mainPageProduct));
            driver.navigate().to("http://localhost/litecart");
            wait.until(urlMatches(assertUrl));
            items = getItems();
        }
    }
    private List<WebElement> getItems() {
        return wait.until((WebDriver d) -> d.findElements(By.className("product")));
    }

    //функция швейцарский-нож, которая валидирует цвет и размер шрифта цен и возвращает строковые значения полей
    private List<String> GetOptionsFromStorePageWithPriceValidation(WebElement we) {
        List<String> options = new ArrayList<>();
        options.add(we.findElement(By.className("name")).getText());

        if (we.findElement(By.className("sticker")).getText().equals("SALE")) {
            options.add(we.findElement(By.className("regular-price")).getText());
            options.add(we.findElement(By.className("campaign-price")).getText());
            assertTrue(IsValidPrice(we));
        } else {
            options.add(we.findElement(By.className("price")).getText());
        }
        return options;
    }
    private List<String> GetOptionsFromProductPageWithPriceValidation(WebElement we) {
        List<String> options = new ArrayList<>();
        options.add(we.findElement(By.className("title")).getText());
        if (we.findElement(By.className("sticker")).getText().equals("SALE")) {
            options.add(we.findElement(By.className("regular-price")).getText());
            options.add(we.findElement(By.className("campaign-price")).getText());
            assertTrue(IsValidPrice(we));
        } else {
            options.add(we.findElement(By.className("price")).getText());
        }
        return options;
    }


    private Boolean IsValidPrice(WebElement we) {
        WebElement regPrice = we.findElement(By.className("regular-price"));
        WebElement salePrice = we.findElement(By.className("campaign-price"));

        assertTrue(regPrice.getCssValue("text-decoration").contains("line-through"));
        Boolean IsValidSize = GetFontSize(regPrice) < GetFontSize(salePrice);
        Boolean IsValidColor = GetFontColor(regPrice).equals("GRAY") && GetFontColor(salePrice).equals("RED");
        return IsValidSize & IsValidColor;
    }
    private double GetFontSize(WebElement price) {
        String toDouble = price.getCssValue("font-size");
        return Double.parseDouble(toDouble.substring(0,toDouble.length() - 2));
    }
    private String GetFontColor(WebElement price) {
        String colorRGBA = price.getCssValue("color");
        String s1 = colorRGBA.substring(5);
        colorRGBA = s1.replace(')', ' ');
        StringTokenizer st = new StringTokenizer(colorRGBA);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        if (g == 0 && b == 0 && r != 0)
            return "RED";
        if (r == g && g == b)
            return "GRAY";
        return "BLACK";
    }

}
