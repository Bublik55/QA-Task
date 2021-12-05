package task.five.is.sorted.zones;

import com.google.common.collect.Ordering;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class IsSortedZones extends TestBase {

    public String login = "admin";
    public String passwd = "admin";
    public String assertUrl = "http://localhost/litecart/admin/";

    @Before
    public void base() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait.until((WebDriver d) -> d.findElement(By.name("username"))).sendKeys(login);
        wait.until((WebDriver d) -> d.findElement(By.name("password"))).sendKeys(passwd);
        wait.until((WebDriver d) -> d.findElement(By.name("login"))).click();
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
    }
    @Test
    public void isSortedZones() {
        wait.until((WebDriver d) -> d.findElement(By.linkText("Geo Zones"))).click();
        List<WebElement> countries = getCountries();
        List<String> countryStringList = new ArrayList<>();
        countries.forEach(webElement -> {
            String name = webElement.findElements(By.cssSelector("td")).get(2).getText();
            countryStringList.add(name);
        });
        countryStringList.forEach(e -> isSortedZones(e));
    }

    public boolean isSortedZones(String Country) {

        Boolean ret;
        wait.until((WebDriver d) -> d.findElement(By.linkText(Country))).click();
        List<WebElement> zones = driver.findElement(By.id("table-zones")).findElements(By.cssSelector("tr"));
        zones.remove(0);
        zones.remove(zones.size()-1);

        List<String> zoneStringList = new ArrayList<>();
        zones.forEach(webElement -> {
            List<WebElement> we = webElement.findElements(By.cssSelector("select")).get(1).findElements(By.cssSelector("option"));
            we.forEach(e -> {
                if(e.isSelected()) {
                    String name = e.getText();
                    zoneStringList.add(name);
                }
            });

        });
        ret = Ordering.natural().isOrdered(zoneStringList);
        wait.until((WebDriver d) -> d.findElement(By.linkText("Geo Zones"))).click();
        return ret;
    }


    private List<WebElement> getCountries() {
        return driver.findElements(By.className("row"));
    }
}