package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import springConstructors.UserData;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Email")
    private WebElement userNameField;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Passwd")
    private WebElement passwordField;

    @FindBy(id = "signIn")
    private WebElement signInBtn;

    private void setUserName(String username) {
        setText(userNameField, username);
    }

    private void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

    private void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        setText(passwordField, password);
    }

    private void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        signInBtn.click();
    }

    public MyAccountPage login(UserData user){
        setUserName(user.getUsername());
        clickNext();
        setPassword(user.getPassword());
        clickSignIn();
        return new MyAccountPage(driver);
    }
}
