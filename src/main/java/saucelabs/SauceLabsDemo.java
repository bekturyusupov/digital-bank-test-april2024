package saucelabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v123.browser.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabsDemo {
    public static void main(String[] args) throws MalformedURLException {
        //WebDriverManager.chromedriver().setup();
        String USERNAME = "oauth-bekturyusupov-225ff";
        String ACCESS_KEY = "a2ba61ff-3c63-4e95-a311-179ee9f5f857";
        String URL = "https://oauth-bekturyusupov-225ff:a2ba61ff-3c63-4e95-a311-179ee9f5f857@ondemand.us-west-1.saucelabs.com:443/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", Platform.WIN10);
        //capabilities.setCapability("browserName", BrowserType.CHROME);
        capabilities.setBrowserName("internet explorer");
        capabilities.setCapability("browserVersion", "latest");

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);

        driver.get("https://dbank-qa.wedevx.co/bank/obp/obp-add");
        driver.findElement(By.id("username")).sendKeys("elonmusk_@yahoo.com");
        driver.findElement(By.id("password")).sendKeys("elonMUSK1");
        driver.findElement(By.id("submit")).click();
        System.out.println(driver.findElement(By.xpath("//div[@class='page-header float-right']")).getText());
        driver.quit();
    }
}
