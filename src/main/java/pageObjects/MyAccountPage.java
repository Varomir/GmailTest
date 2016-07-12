package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdata.DataContainer;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#view_container.Rb")));
    }

    public GmailPage goToMailService(String sufix){
        driver.get(DataContainer.getTestEnv().getMailUrl() + sufix);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("body.aAU")));
        return new GmailPage(driver);
    }
}