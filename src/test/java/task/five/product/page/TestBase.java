package task.five.product.page;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public void startGC() {
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 5);
    }


    public void startIE() {
        System.setProperty("webdriver.edge.driver", "d://dev//msedgedriver95.exe");
        driver = new EdgeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 5);
    }


    public void startFF() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        driver = new FirefoxDriver(caps);
        wait = new WebDriverWait(driver, 5);
    }


    public void stop() {
        driver.quit();
        driver = null;
    }
}
