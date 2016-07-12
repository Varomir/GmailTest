package core;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import springConstructors.TestApp;
import springConstructors.UserData;
import testdata.DataContainer;

@ContextConfiguration(locations={"/spring-config.xml"})
public class AbstractTestRunner extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("account01")
    private UserData account01;

    @Autowired
    @Qualifier("account02")
    private UserData account02;

    @Autowired
    @Qualifier("testEnv")
    private TestApp app;

    protected WebDriver driverSender, driverReceiver;

    @BeforeClass(alwaysRun = true)
    protected void setUp() throws Exception{
        DataContainer.setAccountSender(account01);
        DataContainer.setAccountReceiver(account02);
        DataContainer.setTestEnv(app);
        initWebDriver();
        driverReceiver.get(DataContainer.getTestEnv().getBaseUrl());
        driverSender.get(DataContainer.getTestEnv().getBaseUrl());

    }

    private void initWebDriver() {
        driverReceiver = WebDriverFactory.getDriverInstance();
        driverSender = WebDriverFactory.getDriverInstance();

    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        closeDriver(driverSender);
        closeDriver(driverReceiver);
    }

    private void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
