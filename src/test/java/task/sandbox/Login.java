package task.sandbox;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

public class Login extends TestBase {

    public String login = "admin";
    public String passwd = "admin";
    public String assertUrl = "http://ou112.omsk.obr55.ru/";

    @Test
    public void myFirstTest() {
        driver.navigate().to(assertUrl);
        
        int i = 1;
        while(i > 0) {
            driver.navigate().refresh();
            i++;
            System.out.println(i);
        }
    }
}
