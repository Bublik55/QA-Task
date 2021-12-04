package task.four.open.everysection;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class TestSections extends TestBase {

    public String login = "admin";
    public String passwd = "admin";
    public String assertUrl = "http://localhost/litecart/admin/";

    @Before
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait.until((WebDriver d) -> d.findElement(By.name("username"))).sendKeys(login);
        wait.until((WebDriver d) -> d.findElement(By.name("password"))).sendKeys(passwd);
        wait.until((WebDriver d) -> d.findElement(By.name("login"))).click();
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
    }
    @Test
    public void everyItem() {
        List<WebElement> sections = new ArrayList<>();
        int sectionSize = driver.findElements(By.id("app-")).size();
        for (int i = 0; i < sectionSize; i++) {
            sections = getSections();
            WebElement curSection = sections.get(i);
            curSection.click();
            curSection = getSections().get(i);
            List<WebElement> subSections = getSubSections(curSection);
            int subSectionSize = subSections.size();
            for(int j = 1; j < subSectionSize; j++) {
                sections = getSections();
                WebElement curSubSection = getSubSections(sections.get(i)).get(j);
                curSubSection.click();
                WebElement header = null;
                header = driver.findElement(By.tagName("h1"));
                assertNotNull(header);
            }
        }
        driver.quit();
    }

    private List<WebElement> getSections() {
        return driver.findElements(By.id("app-"));
    }
    private List<WebElement> getSubSections( WebElement curSection) {
        return curSection.findElements(By.className("name"));
    }

}