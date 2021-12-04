package task.four.check.stickers;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class TestSections extends TestBase {

//    public String login = "admin";
//    public String passwd = "admin";
    public String assertUrl = "http://localhost/litecart/en/";

    @Before
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
    }
    @Test
    public void everyItem() {
        List<WebElement> items = getItems();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            WebElement curItem = items.get(i);
            List<WebElement> tag = new ArrayList<>();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
            tag.addAll(curItem.findElements(By.cssSelector(".sticker.new")));
            tag.addAll(curItem.findElements(By.cssSelector(".sticker.sale")));
            assertFalse(tag.isEmpty());
        }
        driver.quit();
    }

    private List<WebElement> getItems() {
        return wait.until((WebDriver d) -> d.findElements(By.className("image-wrapper")));
    }


}
