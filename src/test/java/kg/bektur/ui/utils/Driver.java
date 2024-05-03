package kg.bektur.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;
                case "saucelabs":
                    String platform = ConfigReader.getPropertiesValue("digitalbank.saucelabsplatform");
                    String browserName = ConfigReader.getPropertiesValue("digitalbank.saucelabsbrowser");
                    String browserVersion = ConfigReader.getPropertiesValue("digitalbank.saucelabsbrowser.version");
                    driver = loadSauceLabs(platform, browserName, browserVersion);
                    break;
                default:
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browserName, String browserVersion) {
        String USERNAME = ConfigReader.getPropertiesValue("digitalbank.saucelabsusername");
        String ACCESS_KEY = ConfigReader.getPropertiesValue("digitalbank.saucelabspassword");
        String HOST = ConfigReader.getPropertiesValue("digitalbank.saucelabshost");

        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@" + HOST;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        //capabilities.setBrowserName(ConfigReader.getPropertiesValue("digitalbank.saucelabswebtype"));
        capabilities.setBrowserName(browserName);
        capabilities.setCapability("browserVersion", browserVersion);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
