package task.one.login;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class Login extends TestBase {

    public String login = "admin";
    public String passwd = "admin";
    public String assertUrl = "http://localhost/litecart/admin/";

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until((WebDriver d) -> d.findElement(By.name("username"))).sendKeys(login);
        wait.until((WebDriver d) -> d.findElement(By.name("password"))).sendKeys(passwd);
        wait.until((WebDriver d) -> d.findElement(By.name("login"))).click();
        wait.until(urlMatches(assertUrl));
        assertEquals(driver.getCurrentUrl(),assertUrl);
    }
}
