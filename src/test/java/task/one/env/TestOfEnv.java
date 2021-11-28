package task.one.env;

import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestOfEnv extends TestBase{


    @Test
    public void myFirstTest() {
        String assertString = "webDriver - Поиск в Google";
        driver.navigate().to("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until((WebDriver d) -> d.findElement(By.name("q"))).sendKeys("webDriver");
        wait.until((WebDriver d) -> d.findElement(By.name("btnK"))).click();
        wait.until(titleIs(assertString));
        assertEquals(driver.getTitle(),assertString);
    }
}
