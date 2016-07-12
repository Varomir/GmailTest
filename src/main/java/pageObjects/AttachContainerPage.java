package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachContainerPage extends BasePage {

    public AttachContainerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "pre.aLF-aPX-K0-aPE.aLF-aPX-aLK-ayr-auR")
    private WebElement attachcContainer;

    public int getAttachSize(){
        return attachcContainer.getText().length();
    }

    public String getFullAttachText() {
        return attachcContainer.getText();
    }
}
