package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import testdata.DataContainer;

public class WebDriverFactory {

    public static WebDriver getDriverInstance() {

        WebDriver driverInstance;
        switch (DataContainer.getTestEnv().getDriver()) {
            case "firefox":
                System.out.println(System.getProperty("user.dir"));
                driverInstance = new FirefoxDriver();
                break;
            case "iexplore":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\driver\\IEDriverServer.exe");
                driverInstance = new InternetExplorerDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                driverInstance = new ChromeDriver();
                break;
            default:
                throw new AssertionError("Unsupported browser: " + DataContainer.getTestEnv().getDriver());
        }
        return driverInstance;
    }
}
