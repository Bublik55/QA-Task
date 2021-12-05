package task.five.is.sorted.countries;

import com.google.common.collect.Ordering;
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

public class IsSortedCountries extends TestBase {

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
    public void isSortedCountries() {
        wait.until((WebDriver d) -> d.findElement(By.linkText("Countries"))).click();
        List<WebElement> countries = getCountries();
        List<String> countryStringList = new ArrayList<>();
        countries.forEach(webElement -> {
            String name = webElement.findElements(By.cssSelector("td")).get(4).getText();
            countryStringList.add(name);
        });
        assertTrue(Ordering.natural().isOrdered(countryStringList));
        isSortedZones();
    }

    public void isSortedZones() {
        List<WebElement> countries = getCountries();
        List<String> countryWithZonesList = new ArrayList<>();
        countries.forEach(webElement -> {
            String name = webElement.findElements(By.cssSelector("td")).get(4).getText();
            List<WebElement> curRow = webElement.findElements(By.cssSelector("td"));
            int zones = Integer.parseInt(curRow.get(5).getText());
            if (zones > 0) countryWithZonesList.add(name);
        });
        for(int i = 0; i < countryWithZonesList.size();i++) {
            assertTrue(isSortedZones(countryWithZonesList.get(i)));
        }
    }

    public boolean isSortedZones(String Country) {
        Boolean ret;
        wait.until((WebDriver d) -> d.findElement(By.linkText(Country))).click();
        List<WebElement> zones = driver.findElement(By.id("table-zones")).findElements(By.cssSelector("tr"));
        zones.remove(0);
        zones.remove(zones.size()-1);
        List<String> zoneStringList = new ArrayList<>();
        zones.forEach(webElement -> {
            String name = webElement.findElements(By.cssSelector("td")).get(2).getText();
            zoneStringList.add(name);
        });
        ret = Ordering.natural().isOrdered(zoneStringList);
        wait.until((WebDriver d) -> d.findElement(By.linkText("Countries"))).click();
        return ret;
    }


    private List<WebElement> getCountries() {
        return driver.findElements(By.className("row"));
    }
}